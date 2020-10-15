package com.epam.university.java.core.task005;

import java.util.ArrayList;

/*
 * Completed by Laptev Egor 23.08.2020
 * */

public class Task005Impl implements Task005 {
    @Override
    public PiHolder findPi(int digits) {
        if (digits <= 0 || digits > 10) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> numbers = new ArrayList<>();
        int upBorder = (int) Math.pow(10, digits);
        for (int i = upBorder / 10; i < upBorder; i++) {
            numbers.add(i);
        }
        int sizeOfArray = numbers.size();

        double minimum = 1;
        int resFirst = 0;
        int resSecond = 0;

        for (int i = 0; i < sizeOfArray; i++) { // i = numerator
            int numerator = numbers.get(i);
            for (int j = 0; j <= i / 4; j++) { //j = denominator
                int denominator = numbers.get(j);
                double quotient = (double) numerator / denominator;
                if (denominator <= (numerator / 3) && denominator > (numerator / 4)) {
                    if (Math.abs(quotient - Math.PI) < minimum) {
                        minimum = Math.abs(quotient - Math.PI);
                        resFirst = numerator;
                        resSecond = denominator;
                    }
                }
            }
        }
        return new PiHolderImpl(resFirst, resSecond);
    }
}
