package com.epam.university.java.core.task038;
/*
 * Created by Laptev Egor 12.11.2020
 * */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task038Impl implements Task038 {
    private final ArrayList<List<Integer>> roots = new ArrayList<>();
    private final ArrayList<Integer> possibleRoot = new ArrayList<>();

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
        GraphImpl castedGraph = (GraphImpl) graph;
        int[][] matrix = castedGraph.getMatrix();
        for (int i = 0; i < castedGraph.getCountVertices(); i++) {
            possibleRoot.add(startId);
            search(startId, matrix);
            if (possibleRoot.size() > 1) {
                roots.add(new ArrayList<>(possibleRoot));
                possibleRoot.clear();
            }
        }

        List<Integer> rightRoot = new ArrayList<>();
        for (List<Integer> root : roots) {
            if (root.contains(endId)) {
                rightRoot = root;
            }
        }

        ArrayList<Vertex> rightRootVertex = new ArrayList<>();
        for (Integer integer : rightRoot) {
            if (integer >= startId && integer <= endId) {
                rightRootVertex.add(castedGraph.getVertexById(integer));
            }
        }

        return rightRootVertex;
    }

    /**
     * Searches for possible roots in graph.
     *
     * @param start start vertex
     * @param matrix adjacency matrix
     */
    public void search(int start, int[][] matrix) {
        int nextVert = 0;
        boolean isFind = false;
        for (int j = 0; j < matrix.length; j++) {
            int curr = matrix[start][j];
            if (curr == 1) {
                nextVert = j;
                isFind = true;
                matrix[start][j] = 0;
                break;
            }
        }
        if (isFind) {
            possibleRoot.add(nextVert);
            search(nextVert, matrix);
        }
    }
}
