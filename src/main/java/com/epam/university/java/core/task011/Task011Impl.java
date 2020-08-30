package com.epam.university.java.core.task011;

/*
 * Completed by Laptev Egor 30.08.2020
 * */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Task011Impl implements Task011 {
    @Override
    public String getLastName(String[] collection) {
        if (collection == null || collection.length == 0) {
            throw new IllegalArgumentException();
        }
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(collection));
        int i = 0;
        while (arrayList.size() > 1) {
            if (i >= arrayList.size()) {
                i = i - arrayList.size();
            }
            arrayList.remove(i);
            i++;
        }
        return arrayList.get(0);
    }

    @Override
    public String getLastName(ArrayList<String> collection) {
        if (collection == null || collection.size() == 0) {
            throw new IllegalArgumentException();
        }
        int i = 0;
        while (collection.size() > 1) {
            if (i >= collection.size()) {
                i = i - collection.size();
            }
            collection.remove(i);
            i++;
        }
        return collection.get(0);
    }

    @Override
    public String getLastName(LinkedList<String> collection) {
        if (collection == null || collection.size() == 0) {
            throw new IllegalArgumentException();
        }
        int i = 0;
        while (collection.size() > 1) {
            if (i >= collection.size()) {
                i = i - collection.size();
            }
            collection.remove(i);
            i++;
        }
        return collection.get(0);
    }
}
