package com.epam.university.java.core.task038;
/*
 * Created by Laptev Egor 12.11.2020
 * */

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GraphImpl implements Graph {
    private final List<Vertex> vertices;
    private Map<Vertex, ArrayList<Vertex>> adjMap;
    private final int countVertices;



    public GraphImpl(int countVertices) {
        this.countVertices = countVertices;
        vertices = new ArrayList<>();
        adjMap = new LinkedHashMap<>();
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
        Vertex vertexFrom = getVertexById(fromId);
        Vertex vertexTo = getVertexById(toId);
        if (adjMap.containsKey(vertexFrom)) {
            ArrayList<Vertex> currVer = adjMap.get(vertexFrom);
            currVer.add(vertexTo);
            adjMap.put(vertexFrom, currVer);
        } else {
            ArrayList<Vertex> vert = new ArrayList<>();
            vert.add(vertexTo);
            adjMap.put(vertexFrom, vert);
        }
    }

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

    public Map<Vertex, ArrayList<Vertex>> getAdjMap() {
        return adjMap;
    }
}
