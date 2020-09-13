package com.epam.university.java.core.task014;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/*
 * Completed by Laptev Egor 13.09.2020
 * */

public class Task014Impl implements Task014 {
    @Override
    public Collection<VampireNumber> getVampireNumbers() {
        Map<Integer, VampireNumber> numbers = new TreeMap<>();
        for (int i = 10; i < 100; i++) {
            for (int j = 10; j < 100; j++) {
                int prob = i * j;
                if (checkVampNumber(prob, j, i)) {
                    numbers.put(prob, new VampireNumberFactoryImpl().newInstance(prob, j, i));
                }
            }
        }
        return numbers.values();
    }

    /**
     * Check whether this number vampire or not.
     * @param number number to check
     * @param first first part of number
     * @param second second part of number
     * @return true if this number a vampire number
     */
    public boolean checkVampNumber(int number, int first, int second) {
        StringBuilder stringNumber = new StringBuilder(String.valueOf(number));
        String stringSum = String.valueOf(first) + second;

        if (stringNumber.length() != 4) {
            return false;
        }
        if (first % 10 == 0 && second % 10 == 0) {
            return false;
        }

        for (int i = 0; i < stringSum.length(); i++) {
            char next = stringSum.charAt(i);
            int indexToDelete = stringNumber.indexOf(String.valueOf(next));
            if (indexToDelete == -1) {
                return false;
            }
            stringNumber.deleteCharAt(indexToDelete);
        }

        return true;
    }
}
