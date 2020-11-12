package com.epam.university.java.core.task038;
/*
 * Created by Laptev Egor 12.11.2020
 * */

public class GraphFactoryImpl implements GraphFactory {
    @Override
    public Graph newInstance(int vertexCount) {
        return new GraphImpl(vertexCount);
    }
}
