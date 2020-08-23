package com.epam.university.java.core.task004;

/*
 * Completed by Laptev Egor 23.08.2020
 * */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class Task004Impl implements Task004 {
    @Override
    public String[] intersection(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<String> intersectionList = new ArrayList<>();
        for (String string1 : first) {
            for (String string2 : second) {
                if (string1.equals(string2)) {
                    intersectionList.add(string1);
                }
            }
        }
        return intersectionList.toArray(new String[0]);
    }

    @Override
    public String[] union(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        LinkedHashSet<String> unionSet = new LinkedHashSet<>();
        unionSet.addAll(Arrays.asList(first));
        unionSet.addAll(Arrays.asList(second));
        return unionSet.toArray(new String[0]);
    }
}
