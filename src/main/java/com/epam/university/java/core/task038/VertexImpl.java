package com.epam.university.java.core.task038;
/*
 * Created by Laptev Egor 12.11.2020
 * */

public class VertexImpl implements Vertex {
    private final int id;
    private final int x;
    private final int y;

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

    @Override
    public int hashCode() {
        return 31 * x * y * id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        Vertex vert = (Vertex) obj;
        if (vert.getId() != this.id) {
            return false;
        }
        if (vert.getX() != this.x) {
            return false;
        }
        if (vert.getY() != this.y) {
            return false;
        }
        return true;
    }
}
