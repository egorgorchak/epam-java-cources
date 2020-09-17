package com.epam.university.java.core.task015;

public class SquareImpl implements Square {
    private Point first;
    private Point second;
    private Point third;
    private Point fourth;
    private double diagonal;
    private double side;

    public SquareImpl(Point first, Point second) {
        this.first = first;
        this.second = second;
        this.diagonal = findDiagonal(first, second);
        this.side = diagonal / Math.sqrt(2d);
        this.third = findThird(first);
        this.fourth = findFourth(first);
    }

    public Point findThird(Point point) {
        if (this.first.getX() == this.second.getX()) {
            double delta = Math.cos((45*Math.PI) / 180d) * side;
            return new PointFactoryImpl().newInstance((point.getX() + delta), (point.getY() + delta));
        } else if (this.first.getY() == this.second.getY()) {
            double delta = Math.cos((45*Math.PI) / 180d) * side;
            return new PointFactoryImpl().newInstance((point.getX() + delta), (point.getY() + delta));
        } else {
            return new PointFactoryImpl().newInstance((point.getX() + side), point.getY());
        }
    }

    public Point findFourth(Point point) {
        if (this.first.getX() == this.second.getX()) {
            double delta = Math.cos((45*Math.PI) / 180d) * side;
            return new PointFactoryImpl().newInstance((point.getX() - delta), (point.getY() + delta));
        } else if (this.first.getY() == this.second.getY()) {
            double delta = Math.cos((45*Math.PI) / 180d) * side;
            return new PointFactoryImpl().newInstance((point.getX() + delta), (point.getY() - delta));
        } else {
            return new PointFactoryImpl().newInstance(point.getX(), point.getY() + side);
        }
    }

    public double findDiagonal(Point first, Point second) {
        if (first.getX() == second.getX()) {
            return Math.abs(first.getY() - second.getY());
        } else if (first.getY() == second.getY()) {
            return Math.abs(first.getX() - second.getX());
        } else {
            return Math.abs(first.getX() - second.getX()) * Math.sqrt(2d);
        }
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

    public double getDiagonal() {
        return diagonal;
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
}
