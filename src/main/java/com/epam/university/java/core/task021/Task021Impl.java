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
        ArrayList<BigPoint> thirdVertices = new ArrayList<>();
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
        Point intersect = pointOfIntersect(positions.get(0), thirdVertices.get(0), positions.get(1), thirdVertices.get(1));

        return intersect;
    }

    public double angleBetween2lines(Point p1, Point p2, Point p3) {
        double angle1 = Math.toDegrees(Math.atan2(p1.getX() - p2.getX(), p1.getY() - p2.getY()));
        double angle2 = Math.toDegrees(Math.atan2(p2.getX() - p3.getX(), p2.getY() - p3.getY()));
        return Math.abs(180 - Math.abs(angle1 - angle2));
    }

    public BigPoint triangleThirdVertex(Point p1, Point p2) {
        Point lineMidpoint = new PointFactoryImpl().newInstance(
                (p1.getX() + p2.getX()) / 2d,
                (p1.getY() + p2.getY()) / 2d);
        BigDecimal side = BigDecimal.valueOf(
                Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2)))
                .setScale(17, RoundingMode.HALF_UP);

        BigDecimal height = BigDecimal.valueOf(
                Math.sqrt(3) * side.doubleValue() / 2d)
                .setScale(17, RoundingMode.HALF_UP);

        BigDecimal angle = BigDecimal.valueOf(
                Math.PI / 2d - Math.atan2(p2.getY() - p1.getY(),p2.getX() - p1.getX()))
                .setScale(17, RoundingMode.HALF_UP);
        BigDecimal deltaX = BigDecimal.valueOf(
                height.doubleValue() * Math.cos(angle.doubleValue()))
                .setScale(17, RoundingMode.HALF_UP);
        BigDecimal deltaY = BigDecimal.valueOf(
                height.doubleValue() * Math.cos(Math.PI / 2d - angle.doubleValue()))
                .setScale(17, RoundingMode.HALF_UP);
        return new BigPoint(
                        deltaX.add(BigDecimal.valueOf(lineMidpoint.getX())),
                        BigDecimal.valueOf(lineMidpoint.getY()).subtract(deltaY)
                );
    }

    public BigPoint pointOfIntersect(Point firstLine1, BigPoint firstLine2,
                                  Point secondLine1, BigPoint secondLine2) {
        BigDecimal[] eq1 = new BigDecimal[3];
        eq1[0] = BigDecimal.valueOf(firstLine1.getY()).subtract(firstLine2.getBigDecimalY());
        eq1[1] = firstLine2.getBigDecimalX().subtract(BigDecimal.valueOf(firstLine1.getX()));
        BigDecimal part1 = BigDecimal.valueOf(firstLine1.getX()).multiply(firstLine2.getBigDecimalY());
        BigDecimal part2 = firstLine2.getBigDecimalX().multiply(BigDecimal.valueOf(firstLine1.getY()));
        eq1[2] = part1.subtract(part2);

        BigDecimal[] eq2 = new BigDecimal[3];
        eq2[0] = BigDecimal.valueOf(secondLine1.getY()).subtract(secondLine2.getBigDecimalY());
        eq2[1] = secondLine2.getBigDecimalX().subtract(BigDecimal.valueOf(secondLine1.getX()));
        BigDecimal part3 = BigDecimal.valueOf(secondLine1.getX()).multiply(secondLine2.getBigDecimalY());
        BigDecimal part4 = secondLine2.getBigDecimalX().multiply(BigDecimal.valueOf(secondLine1.getY()));
        eq2[2] = part3.subtract(part4);


        BigDecimal delta1 = eq1[0].multiply(eq2[1]);
        BigDecimal delta2 = eq1[1].multiply(eq2[0]);
        BigDecimal delta = delta1.subtract(delta2).setScale(17, RoundingMode.HALF_UP);

        if (delta.compareTo(BigDecimal.valueOf(0)) == 0) {
            return null;
        }

        BigDecimal deltaX1 = eq1[2].multiply(BigDecimal.valueOf(-1)).multiply(eq2[1]);
        BigDecimal deltaX2 = eq1[1].multiply(BigDecimal.valueOf(-1)).multiply(eq2[2]);
        BigDecimal deltaX = deltaX1.subtract(deltaX2).setScale(17, RoundingMode.HALF_UP);

        BigDecimal deltaY1 = eq1[0].multiply(BigDecimal.valueOf(-1)).multiply(eq2[2]);
        BigDecimal deltaY2 = eq1[2].multiply(BigDecimal.valueOf(-1)).multiply(eq2[0]);

        BigDecimal deltaY = deltaY1.subtract(deltaY2).setScale(17, RoundingMode.HALF_UP);

        BigDecimal x = deltaX.divide(delta, 17, RoundingMode.HALF_UP);
        BigDecimal y = deltaY.divide(delta, 17, RoundingMode.HALF_UP);

        BigPoint inter = new BigPoint(x, y);
        return inter;
    }
}
