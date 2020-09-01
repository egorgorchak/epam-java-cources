package com.epam.university.java.core.task008;

/*
 * Completed by Laptev Egor 27.08.2020
 * */

public class Task008Impl implements Task008 {

    @Override
    public boolean isValid(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sourceString.length(); i++) {
            if (isBracket(sourceString.charAt(i))) {
                stringBuilder.append(sourceString.charAt(i));
            }
        }
        String newSource = stringBuilder.toString();

        while (newSource.contains("()") || newSource.contains("[]") || newSource.contains("{}")) {
            newSource = newSource.replace("()", "");
            newSource = newSource.replace("[]", "");
            newSource = newSource.replace("{}", "");
        }
        return newSource.length() == 0;
    }

    private static boolean isBracket(char ch) {
        return ch == '(' || ch == ')' || ch == '[' || ch == ']' || ch == '{' || ch == '}';
    }
}
