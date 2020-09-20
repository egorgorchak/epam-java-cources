package com.epam.university.java.core.task015;

import java.util.*;

public class Task015Impl implements Task015 {
    @Override
    public double getArea(Square first, Square second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }

        List<Point> sortedPointsOfFirst = sortPointsInRightOrder(first);
        List<Point> sortedPointsOfSecond = sortPointsInRightOrder(second);

        double areaFirst = ((SquareImpl) first).getSide() * ((SquareImpl) first).getSide();
        double areaSecond = ((SquareImpl) second).getSide() * ((SquareImpl) second).getSide();

        List<Point> pointsOfIntersection = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Point point1 = sortedPointsOfFirst.get(i);
            Point point2;
            if (i == 3) {
                point2 = sortedPointsOfFirst.get(0);
            } else {
                point2 = sortedPointsOfFirst.get(i + 1);
            }
            for (int j = 0; j < 4; j++) {
                Point p1 = sortedPointsOfSecond.get(j);
                Point p2;
                if (j == 3) {
                    p2 = sortedPointsOfSecond.get(0);
                } else {
                    p2 = sortedPointsOfSecond.get(j + 1);
                }
                Point intersect = pointOfIntersect(point1, point2, p1, p2);
                if (intersect != null) {
                    pointsOfIntersection.add(intersect);
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            Point point1 = sortedPointsOfFirst.get(i);
            double areaToCheck = 0;
            for (int j = 0; j < 4; j++) {
                Point p1 = sortedPointsOfSecond.get(j);
                Point p2;
                if (j == 3) {
                    p2 = sortedPointsOfSecond.get(0);
                } else {
                    p2 = sortedPointsOfSecond.get(j + 1);
                }
                areaToCheck = areaToCheck + triangleArea(point1, p1, p2);
            }
            if (Math.round(areaToCheck) == Math.round(areaSecond)) {
                pointsOfIntersection.add(point1);
            }
        }

        for (int i = 0; i < 4; i++) {
            Point point1 = sortedPointsOfSecond.get(i);
            double areaToCheck = 0;
            for (int j = 0; j < 4; j++) {
                Point p1 = sortedPointsOfFirst.get(j);
                Point p2;
                if (j == 3) {
                    p2 = sortedPointsOfFirst.get(0);
                } else {
                    p2 = sortedPointsOfFirst.get(j + 1);
                }
                areaToCheck = areaToCheck + triangleArea(point1, p1, p2);
            }
            if (Math.round(areaToCheck) == Math.round(areaFirst)) {
                pointsOfIntersection.add(point1);
            }
        }
        if (pointsOfIntersection.isEmpty()) {
            return 0d;
        }
        LinkedHashSet<Point> points = new LinkedHashSet<>(grahamSort(pointsOfIntersection));
        pointsOfIntersection = new ArrayList<>(points);

        double area = 0;
        for (int i = 0; i < pointsOfIntersection.size(); i++) {
            Point current = pointsOfIntersection.get(i);
            Point next;
            if (i == pointsOfIntersection.size() - 1) {
                next = pointsOfIntersection.get(0);
            } else {
                next = pointsOfIntersection.get(i + 1);
            }
            area = area + (current.getX() * next.getY() - current.getY() * next.getX()) / 2d;
        }

        return Math.abs(area);
    }

    public Point pointOfIntersect(Point firstLine1, Point firstLine2, Point secondLine1, Point secondLine2) {
        double[] eq1 = new double[3];
        double[] eq2 = new double[3];

        eq1[0] = firstLine1.getY() - firstLine2.getY();
        eq1[1] = firstLine2.getX() - firstLine1.getX();
        eq1[2] = firstLine1.getX() * firstLine2.getY() - firstLine2.getX() * firstLine1.getY();

        eq2[0] = secondLine1.getY() - secondLine2.getY();
        eq2[1] = secondLine2.getX() - secondLine1.getX();
        eq2[2] = secondLine1.getX() * secondLine2.getY() - secondLine2.getX() * secondLine1.getY();

        double delta = eq1[0] * eq2[1] - eq1[1] * eq2[0];

        if (delta == 0) {
            return null;
        }

        double deltaX = (eq1[2] * -1d) * eq2[1] - eq1[1] * (eq2[2] * -1d);
        double deltaY = eq1[0] * (eq2[2] * -1d) - (eq1[2] * -1) * eq2[0];

        double x = deltaX / delta;
        double y = deltaY / delta;

        Point inter = new PointFactoryImpl().newInstance(x, y);

        if (isBelongToLine(inter, firstLine1, firstLine2) && isBelongToLine(inter, secondLine1, secondLine2)) {
            return inter;
        }

        return null;
    }

    public boolean isBelongToLine(Point pointToCheck, Point point1, Point point2) {
        double[] eq1 = new double[3];

        eq1[0] = point1.getY() - point2.getY();
        eq1[1] = point2.getX() - point1.getX();
        eq1[2] = point1.getX() * point2.getY() - point2.getX() * point1.getY();

        double delta = eq1[0] * pointToCheck.getX() + eq1[1] * pointToCheck.getY() + eq1[2];

        if (delta != 0) {
            return false;
        }

        double x1 = point1.getX();
        double y1 = point1.getY();
        double x2 = point2.getX();
        double y2 = point2.getY();

        if (x1 >= x2 && y1 >= y2) {
            Point temp = point1;
            point1 = point2;
            point2 = temp;
        }

        if (point1.getX() != point2.getX() && point1.getY() != point2.getY()) {
            return (point1.getY() <= pointToCheck.getY() && point2.getY() >= pointToCheck.getY()) ||
                    (point1.getX() <= pointToCheck.getX() && point2.getX() >= pointToCheck.getX());
        }

        return (point1.getY() <= pointToCheck.getY() && point2.getY() >= pointToCheck.getY()) &&
               (point1.getX() <= pointToCheck.getX() && point2.getX() >= pointToCheck.getX());

    }

    public List<Point> sortPointsInRightOrder(Square square) {
        List<Point> points = new ArrayList<>();
        points.add(square.getFirst());
        points.add(square.getSecond());

        if (square.getFirst().getX() == square.getSecond().getX()) {
            points.add(((SquareImpl) square).getFourth());
            points.add(((SquareImpl) square).getThird());
        } else {
            points.add(((SquareImpl) square).getThird());
            points.add(((SquareImpl) square).getFourth());
        }
        Point leftMin = square.getFirst();

        for (Point point : points) {
            if (leftMin.getX() > point.getX()) {
                leftMin = point;
            } else if (leftMin.getX() == point.getX()) {
                if (leftMin.getY() > point.getY()) {
                    leftMin = point;
                }
            }
        }

        List<Point> sortedPoints = new ArrayList<>();
        sortedPoints.add(leftMin);

        for (Point point : points) {
            if (point.getY() != leftMin.getY() || point.getX() != leftMin.getX()) {
                sortedPoints.add(point);
            }
        }

        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < sortedPoints.size() - 1; i++) {
                Point currentPoint = sortedPoints.get(i);
                Point nextPoint = sortedPoints.get(i + 1);

                double currentAngle = Math.atan2(currentPoint.getY(), currentPoint.getX());
                double nextAngle = Math.atan2(nextPoint.getY(), nextPoint.getX());

                if (nextAngle > currentAngle) {
                    Point temp = sortedPoints.get(i);
                    sortedPoints.set(i, sortedPoints.get(i + 1));
                    sortedPoints.set(i + 1, temp);
                    isSorted = false;
                }
            }

            for (int i = 0; i < sortedPoints.size() - 1; i++) {
                Point currentPoint = sortedPoints.get(i);
                Point nextPoint = sortedPoints.get(i + 1);
                if (currentPoint.getX() == currentPoint.getY() && nextPoint.getX() == nextPoint.getY()) {
                    if (currentPoint.getX() > nextPoint.getX()) {
                        Point temp = sortedPoints.get(i);
                        sortedPoints.set(i, sortedPoints.get(i + 1));
                        sortedPoints.set(i + 1, temp);
                        isSorted = false;
                    }
                }
            }
        }
        return sortedPoints;
    }

    public List<Point> sortPointsInRightOrder(List<Point> points) {
        Point leftMin = points.get(0);

        for (Point point : points) {
            if (leftMin.getX() > point.getX()) {
                leftMin = point;
            } else if (leftMin.getX() == point.getX()) {
                if (leftMin.getY() > point.getY()) {
                    leftMin = point;
                }
            }
        }

        List<Point> sortedPoints = new ArrayList<>();
        sortedPoints.add(leftMin);

        for (Point point : points) {
            if (point.getY() != leftMin.getY() || point.getX() != leftMin.getX()) {
                sortedPoints.add(point);
            }
        }

        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < sortedPoints.size() - 1; i++) {
                Point currentPoint = sortedPoints.get(i);
                Point nextPoint = sortedPoints.get(i + 1);

                double currentAngle = Math.atan2(currentPoint.getY(), currentPoint.getX());
                double nextAngle = Math.atan2(nextPoint.getY(), nextPoint.getX());

                if (nextAngle > currentAngle) {
                    Point temp = sortedPoints.get(i);
                    sortedPoints.set(i, sortedPoints.get(i + 1));
                    sortedPoints.set(i + 1, temp);
                    isSorted = false;
                }
            }
            for (int i = 0; i < sortedPoints.size() - 1; i++) {
                Point currentPoint = sortedPoints.get(i);
                Point nextPoint = sortedPoints.get(i + 1);
                if (currentPoint.getX() == currentPoint.getY() && nextPoint.getX() == nextPoint.getY()) {
                    if (currentPoint.getX() > nextPoint.getX()) {
                        Point temp = sortedPoints.get(i);
                        sortedPoints.set(i, sortedPoints.get(i + 1));
                        sortedPoints.set(i + 1, temp);
                        isSorted = false;
                    }
                }
            }
        }
        return sortedPoints;
    }

    public double triangleArea(Point first, Point second, Point third) {
        double side1 = Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow(first.getY() - second.getY(), 2));
        double side2 = Math.sqrt(Math.pow(second.getX() - third.getX(), 2) + Math.pow(second.getY() - third.getY(), 2));
        double side3 = Math.sqrt(Math.pow(third.getX() - first.getX(), 2) + Math.pow(third.getY() - first.getY(), 2));

        double semiPer = (side1 + side2 + side3) / 2;

        return Math.sqrt(semiPer * (semiPer - side1) * (semiPer - side2) * (semiPer - side3));
    }

    public double squareArea(LinkedHashSet<Point> pointSet) {
        ArrayList<Point> arrayList = new ArrayList<>(pointSet);
        double side = Math.sqrt(Math.pow(arrayList.get(0).getX() - arrayList.get(1).getX(), 2) + Math.pow(arrayList.get(0).getY() - arrayList.get(1).getY(), 2));
        return side * side;
    }

    public List<Point> grahamSort(List<Point> points) {
        Point rightMin = points.get(0);
        for (Point point : points) {
            if (rightMin.getY() > point.getY()) {
                rightMin = point;
            } else if (rightMin.getY() == point.getY()) {
                if (rightMin.getX() < point.getX()) {
                    rightMin = point;
                }
            }
        }
        Point pointWithMaxAngle = null;
        double maxAngle = 0;
        for (Point point : points) {
            double currentAngle = angleBetweenLineAndAbscissa(rightMin, point);
            if (currentAngle > maxAngle) {
                maxAngle = currentAngle;
                pointWithMaxAngle = point;
            }
        }

        points.remove(pointWithMaxAngle);
        points.remove(rightMin);
        points.add(0, pointWithMaxAngle);
        points.add(1, rightMin);

        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 2; i < points.size() - 1; i++) {
                Point currentPoint = points.get(i);
                Point nextPoint = points.get(i + 1);

                double currentAngle = angleBetweenLineAndAbscissa(rightMin, currentPoint);
                double nextAngle = angleBetweenLineAndAbscissa(rightMin, nextPoint);
                if (currentAngle > nextAngle) {
                    Point temp = points.get(i);
                    points.set(i, points.get(i + 1));
                    points.set(i + 1, temp);
                    isSorted = false;
                }
            }
        }

        return points;
    }

    public static double angleBetweenLineAndAbscissa(Point p1, Point p2) {
        return Math.atan2(p2.getY() - p1.getY(), p2.getX() - p1.getX());
    }
}
