package com.epam.university.java.core.task013;

import java.util.ArrayList;
import java.util.Collection;

public class FigureImpl implements Figure {
    private ArrayList<Vertex> vertexes = new ArrayList<>();
    private final int amountOfVert;

    public FigureImpl(int vertexCount) {
        this.amountOfVert = vertexCount;
    }

    @Override
    public void addVertex(Vertex vertex) {
        if (vertexes.size() > amountOfVert) {
            throw new IllegalArgumentException();
        }
        vertexes.add(vertex);
    }

    @Override
    public Collection<Vertex> getVertexes() {
        return vertexes;
    }
}
