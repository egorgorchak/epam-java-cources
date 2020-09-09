package com.epam.university.java.core.task013;

import java.util.ArrayList;
import java.util.Collection;

public class Task013Impl implements Task013 {
    @Override
    public Figure invokeActions(Figure figure, Collection<FigureAction> actions) {
        for (FigureAction action : actions) {
            action.run(figure);
        }
        return figure;
    }

    @Override
    public boolean isConvexPolygon(Figure figure) {
        ArrayList<Vertex> vertexes = new ArrayList<>(figure.getVertexes());
        int checkSign = 0;
        for (int i = 0; i < vertexes.size(); i++) {
            Vertex currentVert = vertexes.get(i);
            Vertex nextVert;
            if (i == vertexes.size() - 1) {
                nextVert = vertexes.get(0); ;
            } else {
                nextVert = vertexes.get(i+1);
            }
            Vertex previousVert;
            if (i == 0) {
                previousVert = vertexes.get(vertexes.size() - 1); ;
            } else {
                previousVert = vertexes.get(i - 1);
            }

            int[] point1 = new int[2];
            int[] point2 = new int[2];

            point1[0] = currentVert.getX() - previousVert.getX();
            point1[1] = currentVert.getY() - previousVert.getY();

            point2[0] = nextVert.getX() - currentVert.getX();
            point2[1] = nextVert.getY() - currentVert.getY();

            int value = point1[0] * point2[1] - point1[1] * point2[0];
            if (i == 0) {
                checkSign = sign(value);
            } else if (sign(value) == 0) {
                continue;
            } else if (checkSign != sign(value)) {
                return false;
            }
        }
        return true;
    }

    public int sign(int x) {
        if (x > 0) {
            return 1;
        } else if (x < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
