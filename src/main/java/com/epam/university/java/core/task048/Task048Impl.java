package com.epam.university.java.core.task048;
/*
 * Completed by Laptev Egor 14.10.2020
 * */

import java.util.ArrayList;
import java.util.Collection;

public class Task048Impl implements Task048 {

    @Override
    public Collection<Integer> getArmstrongNumbers(Integer from, Integer to) {
        if (from == null || to == null || from < 0 || to < 0) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> armstrongNumbers = new ArrayList<>();
        for (int i = from; i < to; i++) {
            int numberOfDigits = (int) Math.ceil(Math.log10(i + 0.5));
            int sum = 0;
            int current = i;
            for (int j = 0; j < numberOfDigits; j++) {
                int remainder = current % 10;
                sum += Math.pow(remainder, numberOfDigits);
                current /= 10;
            }
            if (sum == i) {
                armstrongNumbers.add(i);
            }
        }
        return armstrongNumbers;
    }
}
