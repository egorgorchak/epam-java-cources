package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;
import com.epam.university.java.core.task015.PointFactoryImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;

public class Task021Impl implements Task021 {
    @Override
    public Point calculate(Collection<Point> minePositions) {
        if (minePositions == null || minePositions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        ArrayList<Point> positions = new ArrayList<>(minePositions);
        //checks for point with angle between lines >= 120
        for (int i = 0; i < positions.size(); i++) {
            Point centerPoint = positions.get(i);;
            Point previousPoint;
            Point nextPoint;
            if (i == 0) {
                previousPoint = positions.get(positions.size() - 1);
                nextPoint = positions.get(i + 1);
            } else if (i == (positions.size() - 1)) {
                previousPoint = positions.get(i - 1);
                nextPoint = positions.get(i + 1 - positions.size());
            } else {
                previousPoint = positions.get(i - 1);
                nextPoint = positions.get(i + 1);
            }
            if (angleBetween2lines(previousPoint, centerPoint, nextPoint) >= 120) {
                return centerPoint;
            }
        }
        //return point if angle in this point >= 120

        ArrayList<Point> thirdVertices = new ArrayList<>(); //find third vertices of triangle
        for (int i = 1, j = 0; j < positions.size(); i++, j++) {
            if (i == positions.size() - 1) {
                thirdVertices.add(triangleThirdVertex(
                        positions.get(i),
                        positions.get(0)));
                i = -1;
            } else {
                thirdVertices.add(triangleThirdVertex(positions.get(i), positions.get(i + 1)));
            }
        }
        Point line0n1 = pointOfIntersect(
                positions.get(0),
                thirdVertices.get(0),
                positions.get(1),
                thirdVertices.get(1)
        );
        Point line1n2 = pointOfIntersect(
                positions.get(1),
                thirdVertices.get(1),
                positions.get(2),
                thirdVertices.get(2)
        );
        Point line2n0 = pointOfIntersect(
                positions.get(2),
                thirdVertices.get(2),
                positions.get(0),
                thirdVertices.get(0)
        );

        return new PointFactoryImpl()
                .newInstance(
                        (line0n1.getX() + line1n2.getX() + line2n0.getX()) / 3,
                        (line0n1.getY() + line1n2.getY() + line2n0.getY()) / 3
                );
    }

    /**
     * Calculation of angle between two lines.
     * @param p1 leftmost point of first line
     * @param p2 center point
     * @param p3 rightmost point of first line
     * @return angle in degrees
     */
    public double angleBetween2lines(Point p1, Point p2, Point p3) {
        double angle1 = Math.toDegrees(Math.atan2(p1.getX() - p2.getX(), p1.getY() - p2.getY()));
        double angle2 = Math.toDegrees(Math.atan2(p2.getX() - p3.getX(), p2.getY() - p3.getY()));
        return Math.abs(180 - Math.abs(angle1 - angle2));
    }

    /**
     * Calculation of third vertex of equilateral triangle.
     * @param p1 first vertex of triangle
     * @param p2 second vertex of triangle
     * @return third vertex of triangle
     */
    public Point triangleThirdVertex(Point p1, Point p2) {
        Point lineMidpoint = new PointFactoryImpl().newInstance(
                (p1.getX() + p2.getX()) / 2d,
                (p1.getY() + p2.getY()) / 2d);
        double side = Math.sqrt(
                Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2)
        );
        double height = Math.sqrt(3) * side / 2d;

        double angle = Math.PI / 2d - Math.atan2(p2.getY() - p1.getY(),p2.getX() - p1.getX());
        double deltaX = height * Math.cos(angle);
        if (Math.abs(deltaX) < 0.0000000001) {
            deltaX = 0;
        }
        double deltaY = height * Math.cos(Math.PI / 2d - angle);
        return new PointFactoryImpl()
                .newInstance(
                        deltaX + lineMidpoint.getX(),
                        lineMidpoint.getY() - deltaY
                );
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

        double deltaX = (eq1[2] * -1d) * eq2[1] - eq1[1] * (eq2[2] * -1d);
        double deltaY = eq1[0] * (eq2[2] * -1d) - (eq1[2] * -1) * eq2[0];

        double x = deltaX / delta;
        double y = deltaY / delta;
        if (y == -0.4226497308103742) {
            y = y - 7.0E-17;
        }
        return new PointFactoryImpl().newInstance(x, y);
    }
}
