package com.epam.university.java.core.task056;
/*
 * Created by Laptev Egor 04.11.2020
 * */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task056Impl implements Task056 {
    private List<ArrayList<Integer>> possibleCombinations = new ArrayList<>();
    private List<ArrayList<Integer>> courseOfPills = new ArrayList<>();

    @Override
    public Collection<Integer> necessaryMedication(String prescriptionFile) {
        if (prescriptionFile == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<String> stringsFromFile = new ArrayList<>();
        int maxDays = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(prescriptionFile));
            while (reader.ready()) {
                String nextString = reader.readLine();
                stringsFromFile.add(nextString);
                String[] split = nextString.split("\\D");
                int i = Integer.parseInt(split[2]);
                if (i > maxDays) {
                    maxDays = i;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < stringsFromFile.size(); i++) {
            String[] split = stringsFromFile.get(i).split("\\D+");
            int from = Integer.parseInt(split[1]) - 1;
            int to = Integer.parseInt(split[2]) - 1;
            ArrayList<Integer> toAdd = new ArrayList<>();
            for (int j = 0; j < maxDays; j++) {
                if (j >= from && j<=to) {
                    toAdd.add(1);
                } else {
                    toAdd.add(0);
                }
            }
            courseOfPills.add(toAdd);
        }

        String[] amountOfBoxInCourse = new String[courseOfPills.size()];
        for (int i = 0; i < courseOfPills.size(); i++) {
            amountOfBoxInCourse[i] = String.valueOf(i);
        }
        for (int i = 1; i < amountOfBoxInCourse.length + 1; i++) {
            permuteIteration(amountOfBoxInCourse, 0, i);
        }

        ArrayList<Integer> resultBoxNumbers = new ArrayList<>();
        int maxPills = 0;
        for (int i = 0; i < possibleCombinations.size(); i++) {
            ArrayList<Integer> combinationsArray = possibleCombinations.get(i);
            if (combinationsArray.size() == 1) {
                int index = combinationsArray.get(0);
                ArrayList<Integer> integers = courseOfPills.get(index);
                int sum = integers.stream().mapToInt(a -> a).sum();
                if (sum > maxPills) {
                    maxPills = sum;
                    resultBoxNumbers.clear();
                    resultBoxNumbers.add(index);
                }
                continue;
            }

            if (!isIntersectEachOther(combinationsArray)) {
                List<Integer> integers = sumOfArrays(combinationsArray);
                int sum = integers.stream().mapToInt(a -> a).sum();
                if (sum > maxPills) {
                    resultBoxNumbers.clear();
                    maxPills = sum;
                    resultBoxNumbers.addAll(combinationsArray);
                }
            }
        }
        return resultBoxNumbers;
    }

    @Override
    public Collection<String> intervalBetweenMedication(Collection<Integer> necessaryMedication) {
        if (necessaryMedication == null) {
            throw new IllegalArgumentException();
        }
        List<Integer> integers = sumOfArrays(necessaryMedication);
        int i1 = integers.indexOf(1);
        int i2 = integers.lastIndexOf(1);
        ArrayList<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int from = 0;
        int to = 0;
        for (int i = i1; i < i2 - 1; i++) {
            int prev = 0;
            if (i != 0) {
                prev = integers.get(i - 1);
            }
            int current = integers.get(i);
            int next = integers.get(i + 1);
            if (current == 0 && prev == 1) {
                from = i + 1;
            }
            if (current == 0 && next == 1) {
                to = i + 1;
                stringBuilder.append(from).append("-").append(to);
                result.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                from = 0;
                to = 0;
            }
        }
        return result;
    }

    public List<Integer> sumOfArrays(Collection<Integer> arr) {
        List<Integer> res = new ArrayList<>();
        for (Integer integer : arr) {
            List<Integer> integers = courseOfPills.get(integer);
            if (res.isEmpty()) {
                res.addAll(integers);
            } else {
                for (int j = 0; j < integers.size(); j++) {
                    int integer1 = integers.get(j);
                    int integer2 = res.get(j);
                    res.set(j, integer1 + integer2);
                }
            }
        }
        return res;
    }

    public boolean isIntersectEachOther(List<Integer> arr) {
        List<Integer> res = sumOfArrays(arr);
        for (Integer re : res) {
            if (re > 1) {
                return true;
            }
        }
        return false;
    }

    private void permuteIteration(String[] arr, int index, int limit) {
        //последняя итерация
        if (index >= limit) {
            for (int i = 0; i < limit - 1; i++) {
                int i1 = Integer.parseInt(arr[i]);
                int i2 = Integer.parseInt(arr[i + 1]);
                if (i1 > i2) {
                    return;
                }
            }
            ArrayList<Integer> combinations = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                combinations.add(Integer.parseInt(arr[i]));
            }
            possibleCombinations.add(combinations);
            return;
        }

        for (int i = index; i < arr.length; i++) {
            String temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;

            permuteIteration(arr, index + 1, limit);
            temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }
}
