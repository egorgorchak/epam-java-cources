package com.epam.university.java.core.task015;

public class SquareImpl implements Square {
    private Point first;
    private Point second;
    private Point third;
    private Point fourth;
    private double side;

    public SquareImpl(Point first, Point second) {
        this.first = first;
        this.second = second;
        findAllVertices();
    }

    public SquareImpl(Point first, Point second, Point third, Point fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public void findAllVertices () {
        double xc = (first.getX() + second.getX()) / 2;
        double yc = (first.getY() + second.getY()) / 2;

        double xd = (first.getX() - second.getX()) / 2;
        double yd = (first.getY() - second.getY()) / 2;

        third = new PointFactoryImpl().newInstance((xc - yd), (yc + xd));
        fourth = new PointFactoryImpl().newInstance((xc + yd), (yc - xd));

        double diagonal;
        if (first.getX() == second.getX()) {
            diagonal = Math.abs(first.getY() - second.getY());
        } else if (first.getY() == second.getY()) {
            diagonal = Math.abs(first.getX() - second.getX());
        } else {
            diagonal = Math.abs(first.getX() - second.getX()) * Math.sqrt(2d);
        }
        side = diagonal / Math.sqrt(2d);
    }

//    public Point findThird(Point point) {
//        if (this.first.getX() == this.second.getX()) {
//            double delta = Math.cos((45*Math.PI) / 180d) * side;
//            return new PointFactoryImpl().newInstance((point.getX() + delta), (point.getY() + delta));
//        } else if (this.first.getY() == this.second.getY()) {
//            double delta = Math.cos((45*Math.PI) / 180d) * side;
//            return new PointFactoryImpl().newInstance((point.getX() + delta), (point.getY() + delta));
//        } else {
//            return new PointFactoryImpl().newInstance((point.getX() + side), point.getY());
//        }
//    }

//    public Point findFourth(Point point) {
//        if (this.first.getX() == this.second.getX()) {
//            double delta = Math.cos((45*Math.PI) / 180d) * side;
//            return new PointFactoryImpl().newInstance((point.getX() - delta), (point.getY() + delta));
//        } else if (this.first.getY() == this.second.getY()) {
//            double delta = Math.cos((45*Math.PI) / 180d) * side;
//            return new PointFactoryImpl().newInstance((point.getX() + delta), (point.getY() - delta));
//        } else {
//            return new PointFactoryImpl().newInstance(point.getX(), point.getY() + side);
//        }
//    }

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
}
