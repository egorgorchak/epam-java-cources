package com.epam.university.java.core.task024;

/*
 * Completed by Laptev Egor 30.08.2020
 * */

import java.util.ArrayList;
import java.util.Collection;

public class Task024Impl implements Task024 {
    @Override
    public Collection<String> getWordsCount(String source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<String> splitWords = new ArrayList<>();
        int begOfWord = 0;
        for (int i = 0; i < source.length(); i++) {
            char capitalChar = source.charAt(i);
            if (!Character.isLowerCase(capitalChar) && i > 0) {
                splitWords.add(source.substring(begOfWord, i).toLowerCase());
                begOfWord = i;
            } else if (i == source.length() - 1) {
                splitWords.add(source.substring(begOfWord, i + 1).toLowerCase());
            }
        }
        return splitWords;
    }
}
