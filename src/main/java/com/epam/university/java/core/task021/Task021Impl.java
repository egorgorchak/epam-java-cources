package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;

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


        return null;
    }

    public double angleBetween2lines(Point p1, Point p2, Point p3) {
        double angle1 = Math.toDegrees(Math.atan2(p1.getX() - p2.getX(), p1.getY() - p2.getY()));
        double angle2 = Math.toDegrees(Math.atan2(p2.getX() - p3.getX(), p2.getY() - p3.getY()));
        return Math.abs(180 - Math.abs(angle1 - angle2));
    }
}
