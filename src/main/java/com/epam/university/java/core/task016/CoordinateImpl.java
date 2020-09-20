package com.epam.university.java.core.task016;

public class CoordinateImpl implements Coordinate {
    private int x;
    private int y;

    /**
     * Constructor for point.
     * @param x first coordinate
     * @param y second coordinate
     */
    public CoordinateImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
