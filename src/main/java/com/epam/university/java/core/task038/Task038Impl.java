package com.epam.university.java.core.task038;
/*
 * Created by Laptev Egor 12.11.2020
 * */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Task038Impl implements Task038 {
    private ArrayList<List<Vertex>> roots = new ArrayList<>();
    private Vertex endVertex;
    private GraphImpl castedGraph;
    ArrayList<Vertex> possibleRoot = new ArrayList<>();


    @Override
    public Graph invokeActions(Graph sourceGraph, Collection<GraphAction> actions) {
        if (sourceGraph == null || actions == null || actions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (GraphAction action : actions) {
            action.run(sourceGraph);
        }
        return sourceGraph;
    }

    @Override
    public Collection<Vertex> getShortestPath(Graph graph, int startId, int endId) {
        if (graph == null) {
            throw new IllegalArgumentException();
        }
        castedGraph = (GraphImpl) graph;
        Map<Vertex, ArrayList<Vertex>> adjMap = castedGraph.getAdjMap();
        Vertex startVertex = castedGraph.getVertexById(startId);
        endVertex = castedGraph.getVertexById(endId);

        ArrayList<Vertex> visited = new ArrayList<>();
        search(startVertex, adjMap);

        return null;
    }

    public void search(Vertex start, Map<Vertex, ArrayList<Vertex>> adjMap) {
        possibleRoot.add(start);

        ArrayList<Vertex> vertices = adjMap.get(start);

        for (Vertex vertex : vertices) {
            if (vertex.equals(endVertex)) {
                possibleRoot.add(vertex);
                roots.add(possibleRoot);
                possibleRoot.clear();
                return;
            }
        }
        int id = start.getId();
        Vertex vertexById = castedGraph.getVertexById(id + 1);
        search(vertexById, adjMap);
    }
}
