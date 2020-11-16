package com.epam.university.java.core.task063;
/*
 * Created by Laptev Egor 16.11.2020
 * */

public class Task063Impl implements Task063 {
    @Override
    public Integer calcDigitalRoot(Integer number) {
        if (number == null) {
            throw new IllegalArgumentException();
        }
        String s = String.valueOf(number);
        if (s.length() == 1) {
            return number;
        }
        int sum = 0;
        while (String.valueOf(number).length() > 1) {
            sum = 0;
            char[] chars = String.valueOf(number).toCharArray();
            for (char aChar : chars) {
                int numericValue = Character.getNumericValue(aChar);
                sum += numericValue;
            }
            number = sum;

        }
        return sum;
    }
}
