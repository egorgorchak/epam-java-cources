package com.epam.university.java.core.task015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/*
 * Completed by Laptev Egor 20.09.2020
 * */

public class Task015Impl implements Task015 {
    @Override
    public double getArea(Square first, Square second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }

        List<Point> sortedPointsOfFirst = pointSort(((SquareImpl) first).getPointList());
        List<Point> sortedPointsOfSecond = pointSort(((SquareImpl) second).getPointList());

        List<Point> pointsOfIntersection = new ArrayList<>();

        for (int i = 0; i < sortedPointsOfFirst.size(); i++) {
            Point point1 = sortedPointsOfFirst.get(i);
            Point point2;
            if (i == 3) {
                point2 = sortedPointsOfFirst.get(0);
            } else {
                point2 = sortedPointsOfFirst.get(i + 1);
            }
            for (int j = 0; j < sortedPointsOfSecond.size(); j++) {
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

        double areaFirst = ((SquareImpl) first).getSide() * ((SquareImpl) first).getSide();
        double areaSecond = ((SquareImpl) second).getSide() * ((SquareImpl) second).getSide();
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
                areaToCheck = areaToCheck + areaByCoordinates(Arrays.asList(point1, p1, p2));
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
                areaToCheck = areaToCheck + areaByCoordinates(Arrays.asList(point1, p1, p2));
            }
            if (Math.round(areaToCheck) == Math.round(areaFirst)) {
                pointsOfIntersection.add(point1);
            }
        }

        if (pointsOfIntersection.isEmpty()) {
            return 0d;
        }

        LinkedHashSet<Point> points = new LinkedHashSet<>(pointSort(pointsOfIntersection));

        return areaByCoordinates(points);
    }

    /**
     * Calculation the intersection point of two lines.
     * @param firstLine1 first point of first line
     * @param firstLine2 second point of first line
     * @param secondLine1 first point of second line
     * @param secondLine2 second point of second line
     * @return point of intersection
     */
    public Point pointOfIntersect(Point firstLine1, Point firstLine2,
                                  Point secondLine1, Point secondLine2) {
        double[] eq1 = new double[3];
        eq1[0] = firstLine1.getY() - firstLine2.getY();
        eq1[1] = firstLine2.getX() - firstLine1.getX();
        eq1[2] = firstLine1.getX() * firstLine2.getY() - firstLine2.getX() * firstLine1.getY();

        double[] eq2 = new double[3];
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
        if (isBelongToLine(inter, firstLine1, firstLine2)
                && isBelongToLine(inter, secondLine1, secondLine2)) {
            return inter;
        }

        return null;
    }

    /**
     * Checks whether the point belong to the line.
     * @param pointToCheck point to check
     * @param point1 first point of line
     * @param point2 second point of line
     * @return true if pointToCheck belongs to the line
     */
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
            return (point1.getY() <= pointToCheck.getY() && point2.getY() >= pointToCheck.getY())
                    || (point1.getX() <= pointToCheck.getX() && point2.getX() >= pointToCheck.getX());
        }

        return (point1.getY() <= pointToCheck.getY() && point2.getY() >= pointToCheck.getY())
                && (point1.getX() <= pointToCheck.getX() && point2.getX() >= pointToCheck.getX());

    }

    /**
     * Calculation the area of polygon by its coordinates.
     * @param points points of polygon
     * @return area of polygon
     */
    public double areaByCoordinates(Collection<Point> points) {
        ArrayList<Point> pointsOfIntersection = new ArrayList<>(points);
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

    /**
     * Sort the list of points in a counterclockwise direction.
     * @param points list of points
     * @return sorted list of points
     */
    public List<Point> pointSort(List<Point> points) {
        ArrayList<Point> result = new ArrayList<>(points);
        Point rightMin = result.get(0);
        for (Point point : result) {
            if (rightMin.getY() > point.getY()) {
                rightMin = point;
            } else if (rightMin.getY() == point.getY()) {
                if (rightMin.getX() < point.getX()) {
                    rightMin = point;
                }
            }
        }
        Point pointWithMaxAngle = result.get(0);
        double maxAngle = 0;
        for (Point point : result) {
            double currentAngle = Math.atan2(
                    point.getY() - rightMin.getY(),
                    point.getX() - rightMin.getX());
            if (currentAngle > maxAngle) {
                maxAngle = currentAngle;
                pointWithMaxAngle = point;
            }
        }

        result.remove(pointWithMaxAngle);
        result.remove(rightMin);
        result.add(0, pointWithMaxAngle);
        result.add(1, rightMin);

        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 2; i < result.size() - 1; i++) {
                Point currentPoint = result.get(i);
                Point nextPoint = result.get(i + 1);

                double currentAngle = Math.atan2(
                        rightMin.getY() - currentPoint.getY(), 
                        rightMin.getX() - currentPoint.getX());
                double nextAngle = Math.atan2(
                        rightMin.getY() - nextPoint.getY(), 
                        rightMin.getX() - nextPoint.getX());
                if (currentAngle > nextAngle) {
                    Point temp = result.get(i);
                    result.set(i, result.get(i + 1));
                    result.set(i + 1, temp);
                    isSorted = false;
                }
            }
        }
        return result;
    }
}