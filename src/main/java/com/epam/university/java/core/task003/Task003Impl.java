package com.epam.university.java.core.task003;

/*
 * Completed by Laptev Egor 23.08.2020
 * */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;

public class Task003Impl implements Task003 {

    @Override
    public String[] invert(String[] source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        List<String> convertArrayToList = Arrays.asList(source);
        Collections.reverse(convertArrayToList);
        return convertArrayToList.toArray(new String[source.length]);
    }

    @Override
    public String[] join(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        String[] result = new String[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    @Override
    public int findMax(int[] source) {
        if (source == null || source.length == 0) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(source).max().getAsInt();
    }

    @Override
    public String[] filter(String[] source, FilteringCondition condition) {
        if (source == null || condition == null) {
            throw new IllegalArgumentException();
        }
        List<String> filteredArray = new ArrayList<>();
        for (String str : source) {
            if (condition.isValid(str)) {
                filteredArray.add(str);
            }
        }
        return filteredArray.toArray(new String[0]);
    }

    @Override
    public String[] removeElements(String[] source, String[] toRemote) {
        if (source == null || toRemote == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<String> sourceArrayToList = new ArrayList<>(Arrays.asList(source));
        List<String> removeArrayToList = Arrays.asList(toRemote);
        sourceArrayToList.removeAll(removeArrayToList);
        return sourceArrayToList.toArray(new String[0]);
    }

    @Override
    public String[] map(String[] source, MappingOperation operation) {
        if (source == null || operation == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < source.length; i++) {
            source[i] = operation.map(source[i]);
        }
        return source;
    }

    @Override
    public String[] flatMap(String[] source, FlatMappingOperation operation) {
        if (source == null || operation == null) {
            throw new IllegalArgumentException();
        }
        List<String[]> listOfDecompSource = new ArrayList<>();
        for (String string : source) {
            listOfDecompSource.add(operation.flatMap(string));
        }
        HashSet<String> resultSet = new HashSet<>();
        for (String[] str : listOfDecompSource) {
            resultSet.addAll(Arrays.asList(str));
        }
        List<String> resultList = new ArrayList<>(resultSet);
        List<Integer> parseStringToInt = new ArrayList<>();
        for (String string : resultList) {
            parseStringToInt.add(Integer.valueOf(string));
        }
        resultList.clear();
        Collections.sort(parseStringToInt);
        Collections.reverse(parseStringToInt);

        for (Integer integer : parseStringToInt) {
            resultList.add(String.valueOf(integer));
        }
        return resultList.toArray(new String[0]);
    }

}
