package com.epam.university.java.core.task051;
/*
 * Created by Laptev Egor 05.11.2020
 * */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task051Impl implements Task051 {
    @Override
    public String replace(String source) {
        if (source == null || source.isBlank() || source.equalsIgnoreCase("the")) {
            throw new IllegalArgumentException();
        }
        if (Pattern.matches("[A-Z\\s]+", source)) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder(source);
        Pattern compile = Pattern.compile("[the]{3}");
        Matcher matcher = compile.matcher(stringBuilder);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            char c = stringBuilder.charAt(end + 1);
            if (isVowel(c)) {
                stringBuilder.replace(start, end, "an");
            } else {
                stringBuilder.replace(start, end, "a");
            }
            matcher.region(0, stringBuilder.length());
        }
        return stringBuilder.toString();
    }

    public boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
}
