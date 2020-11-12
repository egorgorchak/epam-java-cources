package com.epam.university.java.core.task038;
/*
 * Created by Laptev Egor 12.11.2020
 * */

public class VertexImpl implements Vertex {
    private final int id;
    private final int x;
    private final int y;

    /**
     * Constructor.
     *
     * @param id vertex id
     * @param x first coordinate
     * @param y second coordinate
     */
    public VertexImpl(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
