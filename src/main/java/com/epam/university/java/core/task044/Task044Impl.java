package com.epam.university.java.core.task044;
/*
 * Created by Laptev Egor 17.11.2020
 * */

import java.util.ArrayList;
import java.util.List;

public class Task044Impl implements Task044 {
    @Override
    public int findCountOfTraces(Integer points, List<String> lines) {
        if (points == null || lines == null) {
            throw new IllegalArgumentException();
        }
        if (lines.isEmpty()) {
            return points;
        }

        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 1; i <= points; i++) {
            nodes.add(new Node(i));
        }

        for (String string : lines) {
            String[] split = string.split("\\s");
            int firstNode = Integer.parseInt(split[0]);
            int secondNode = Integer.parseInt(split[1]);

            Node parent = null;
            Node child = null;
            for (Node node : nodes) {
                if (node.getId() == firstNode) {
                    parent = node;
                } else if (node.getId() == secondNode) {
                    child = node;
                }
            }
            parent.setNeighborhood(child);
        }
        int result = 1;
        for (Node node : nodes) {
            if (node.getNeighborhood() == null && node.getId() != points) {
                result++;
            }
        }
        return result;
    }
}
