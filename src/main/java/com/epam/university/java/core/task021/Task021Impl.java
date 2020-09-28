package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;
import com.epam.university.java.core.task015.PointFactoryImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        ArrayList<Point> vert = new ArrayList<>();
        vert.add(triangleThirdVertex(positions.get(0), positions.get(1)));
        vert.add(triangleThirdVertex(positions.get(1), positions.get(2)));
        vert.add(triangleThirdVertex(positions.get(2), positions.get(0)));



        return null;
    }

    public double angleBetween2lines(Point p1, Point p2, Point p3) {
        double angle1 = Math.toDegrees(Math.atan2(p1.getX() - p2.getX(), p1.getY() - p2.getY()));
        double angle2 = Math.toDegrees(Math.atan2(p2.getX() - p3.getX(), p2.getY() - p3.getY()));
        return Math.abs(180 - Math.abs(angle1 - angle2));
    }

    public Point triangleThirdVertex(Point p1, Point p2) {
        Point lineMidpoint = new PointFactoryImpl().newInstance(
                (p1.getX() + p2.getX()) / 2d,
                (p1.getY() + p2.getY()) / 2d);
        BigDecimal side = new BigDecimal(Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2)));
        BigDecimal height = new BigDecimal(Math.sqrt(3) * side.doubleValue() / 2d);
        BigDecimal angle = new BigDecimal(Math.PI / 2d - Math.atan2(p2.getY() - p1.getY(), p2.getX() - p1.getX()));
        BigDecimal deltaX = new BigDecimal(height.doubleValue() * Math.cos(angle.doubleValue())).setScale(17, RoundingMode.HALF_UP);
        BigDecimal deltaY = new BigDecimal(height.doubleValue() * Math.cos(Math.PI / 2d - angle.doubleValue())).setScale(17, RoundingMode.HALF_UP);
        return new PointFactoryImpl().newInstance(lineMidpoint.getX() + deltaX.doubleValue(), lineMidpoint.getY() - deltaY.doubleValue());
    }
}
