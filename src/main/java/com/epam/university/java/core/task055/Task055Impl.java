package com.epam.university.java.core.task055;
/*
 * Created by Laptev Egor 16.11.2020
 * */

public class Task055Impl implements Task055 {
    @Override
    public ProcessingContext createContext(String path) {
        if (path == null) {
            throw new IllegalArgumentException();
        }
        return new ProcessingContextImpl(path);
    }
}
