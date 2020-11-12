package com.epam.university.java.core.task038;
/*
 * Created by Laptev Egor 12.11.2020
 * */

import java.util.ArrayList;
import java.util.List;

public class GraphImpl implements Graph {
    private final List<Vertex> vertices;
    private int[][] matrix;

    private final int countVertices;

    /**
     * Constructor.
     *
     * @param countVertices amount of vertices
     */
    public GraphImpl(int countVertices) {
        this.countVertices = countVertices;
        vertices = new ArrayList<>();
        matrix = new int[countVertices][countVertices];
    }

    @Override
    public void createVertex(int id, int x, int y) {
        if (vertices.size() >= countVertices) {
            throw new IllegalArgumentException();
        }
        vertices.add(new VertexImpl(id, x, y));
    }

    @Override
    public void connectVertices(int fromId, int toId) {
        matrix[fromId][toId] = 1;
    }

    /**
     * Get vertex by id.
     *
     * @param id vertex id
     * @return vertex instance
     */
    public Vertex getVertexById(int id) {
        Vertex result = null;
        for (Vertex vertex : vertices) {
            if (vertex.getId() == id) {
                result = vertex;
                break;
            }
        }
        return result;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getCountVertices() {
        return countVertices;
    }
}
