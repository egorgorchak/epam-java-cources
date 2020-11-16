package com.epam.university.java.core.task061;
/*
 * Created by Laptev Egor 16.11.2020
 * */

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Task061Impl implements Task061 {
    private final Map<String, Integer> map = new LinkedHashMap<>();

    {
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);
    }

    @Override
    public String convertToRoman(int number) {
        if (number > 4000) {
            throw new IllegalArgumentException();
        }
        StringBuilder result = new StringBuilder();

        while (number > 0) {
            for (Map.Entry<String, Integer> pair : map.entrySet()) {
                String key = pair.getKey();
                Integer value = pair.getValue();
                if (value <= number) {
                    result.append(key);
                    number -= value;
                }
            }
        }
        return result.toString();
    }

    @Override
    public int convertToArabic(String number) {
        if (number == null) {
            throw new IllegalArgumentException();
        }
        if (!Pattern.matches("[MDCLXVI]+", number)) {
            throw new IllegalArgumentException();
        }
        char[] chars = number.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            char curr = chars[i];
            char next = 0;
            if (i < chars.length - 2) {
                next = chars[i + 1];
            }
            int valueCurr = map.get(String.valueOf(curr));
            int valueNext = 0;
            if (i < chars.length - 2) {
                valueNext = map.get(String.valueOf(next));
            }
            if (valueCurr >= valueNext) {
                result += valueCurr;
            } else {
                result += valueNext - valueCurr;
                i++;
            }
        }
        return result;
    }
}
