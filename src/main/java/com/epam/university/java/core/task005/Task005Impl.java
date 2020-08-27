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
        double minimum = 1;
        PiHolderImpl holder = new PiHolderImpl(0, 0);

        for (int i = 0; i < numbers.size(); i++) { // i = numerator
            int numerator = numbers.get(i);
            for (int j = 0; j <= i / 3; j++) { //j = denominator
                int denominator = numbers.get(j);
                if (denominator <= (numerator / 3) && denominator > (numerator / 4)) {
                    double currentEx = Math.abs(((double) numerator / denominator) - Math.PI);
                    if (currentEx < minimum) {
                        minimum = currentEx;
                        holder.setFirst(numerator);
                        holder.setSecond(denominator);
                    }
                }
            }
        }
        return holder;
    }
}
