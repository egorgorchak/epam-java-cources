package com.epam.university.java.core.task015;

import java.util.Arrays;
import java.util.List;

public class SquareImpl implements Square {
    private Point first;
    private Point second;
    private Point third;
    private Point fourth;
    private double side;
    private List<Point> pointList;

    /**
     * Square constructor with two points arranged diagonally.
     * @param first first point of square
     * @param second second point of square
     */
    public SquareImpl(Point first, Point second) {
        this.first = first;
        this.second = second;
        findAllVertices();
    }

    /**
     * Calculation of third and fourth vertices of square.
     */
    public void findAllVertices() {
        double xc = (first.getX() + second.getX()) / 2;
        double yc = (first.getY() + second.getY()) / 2;

        double xd = (first.getX() - second.getX()) / 2;
        double yd = (first.getY() - second.getY()) / 2;

        setThird(new PointFactoryImpl().newInstance((xc - yd), (yc + xd)));
        setFourth(new PointFactoryImpl().newInstance((xc + yd), (yc - xd)));

        double diagonal;
        if (first.getX() == second.getX()) {
            diagonal = Math.abs(first.getY() - second.getY());
        } else if (first.getY() == second.getY()) {
            diagonal = Math.abs(first.getX() - second.getX());
        } else {
            diagonal = Math.abs(first.getX() - second.getX()) * Math.sqrt(2d);
        }
        setSide(diagonal / Math.sqrt(2d));
        setPointList(Arrays.asList(getFirst(), getSecond(), getThird(), getFourth()));
    }

    @Override
    public Point getFirst() {
        return first;
    }

    @Override
    public Point getSecond() {
        return second;
    }

    public Point getThird() {
        return third;
    }

    public Point getFourth() {
        return fourth;
    }

    public double getSide() {
        return side;
    }

    @Override
    public void setFirst(Point first) {
        this.first = first;
    }

    @Override
    public void setSecond(Point second) {
        this.second = second;
    }

    public void setThird(Point third) {
        this.third = third;
    }

    public void setFourth(Point fourth) {
        this.fourth = fourth;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }
}
