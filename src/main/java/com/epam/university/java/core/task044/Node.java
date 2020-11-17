package com.epam.university.java.core.task044;
/*
 * Created by Laptev Egor 17.11.2020
 * */

public class Node {
    private int id;
    private Node neighborhood;

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Node getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Node neighborhood) {
        this.neighborhood = neighborhood;
    }
}
