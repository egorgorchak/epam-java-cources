package com.epam.university.java.core.task052;
/*
 * Created by Laptev Egor 16.11.2020
 * */

import java.util.ArrayList;

public class Task052Impl implements Task052 {
    @Override
    public boolean validateCard(long number) {
        if (number < 0 || String.valueOf(number).length() < 2) {
            throw new IllegalArgumentException();
        }
        String numberToString = String.valueOf(number);
        int sourceNumberLength = numberToString.length();
        if (sourceNumberLength < 1) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> reversedInt = new ArrayList<>();
        for (int i = sourceNumberLength - 2; i >= 0; i--) {
            reversedInt.add(Character.getNumericValue(numberToString.charAt(i)));
        }
        for (int i = 0; i < reversedInt.size(); i += 2) {
            int nextNum = reversedInt.get(i) * 2;
            String nextNumString = String.valueOf(nextNum);
            if (nextNumString.length() > 1) {
                int digitsSum = 0;
                for (int j = 0; j < nextNumString.length(); j++) {
                    digitsSum += Character.getNumericValue(nextNumString.charAt(j));
                }
                reversedInt.set(i, digitsSum);
            } else {
                reversedInt.set(i, nextNum);
            }
        }
        int checkDigit = Character.getNumericValue(numberToString.charAt(sourceNumberLength - 1));
        int checkSum = 0;
        for (Integer integer : reversedInt) {
            checkSum += integer;
        }
        String checkSumString = String.valueOf(checkSum);
        int check = 10 - Character
                .getNumericValue(checkSumString.charAt(checkSumString.length() - 1));
        return check == checkDigit;
    }
}
