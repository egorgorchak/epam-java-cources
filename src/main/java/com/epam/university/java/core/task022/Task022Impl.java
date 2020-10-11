package com.epam.university.java.core.task022;

import java.util.ArrayList;
import java.util.Collection;

/*
 * Completed by Laptev Egor 04.10.2020
 * */

public class Task022Impl implements Task022 {
    @Override
    public int maxSum(Collection<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> numbersList = new ArrayList<>(numbers);
        int maxSum = 0;
        for (int i = 0; i < numbersList.size(); i++) {
            int currentMaxSum = 0;
            for (int j = 0; j < numbersList.size(); j++) {
                if (i == j) {
                    continue;
                }
                currentMaxSum += numbersList.get(j);
            }
            if (i == 0) {
                maxSum = currentMaxSum;
            } else if (maxSum < currentMaxSum) {
                maxSum = currentMaxSum;
            }
        }
        return maxSum;
    }

    @Override
    public int minSum(Collection<Integer> numbers) {
        ArrayList<Integer> numbersList = new ArrayList<>(numbers);
        int minSum = 0;
        for (int i = 0; i < numbersList.size(); i++) {
            int currentMinSum = 0;
            for (int j = 0; j < numbersList.size(); j++) {
                if (i == j) {
                    continue;
                }
                currentMinSum += numbersList.get(j);
            }
            if (i == 0) {
                minSum = currentMinSum;
            } else if (minSum > currentMinSum) {
                minSum = currentMinSum;
            }
        }
        return minSum;
    }
}
