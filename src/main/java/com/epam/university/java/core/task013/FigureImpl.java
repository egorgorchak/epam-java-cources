package com.epam.university.java.core.task013;

import java.util.ArrayList;
import java.util.Collection;

public class FigureImpl implements Figure {
    ArrayList<Vertex> vertexes = new ArrayList<>();

    @Override
    public void addVertex(Vertex vertex) {
        vertexes.add(vertex);
    }

    @Override
    public Collection<Vertex> getVertexes() {
        return vertexes;
    }
}
