package com.epam.university.java.core.task020;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Completed by Laptev Egor 21.09.2020
 * */

public class Task020Impl implements Task020 {
    @Override
    public int calculate(Collection<String> stones) {
        if (stones == null || stones.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<String> stonesToList = new ArrayList<>(stones);
        int gems = 0;
        String firstStone = removeDuplicateCharacters(stonesToList.get(0));
        for (int i = 0; i < firstStone.length(); i++) {
            char charOfFirstStone = firstStone.charAt(i);
            int result = 0;
            for (int j = 1; j < stonesToList.size(); j++) {
                String currentStone = removeDuplicateCharacters(stonesToList.get(j));
                if (currentStone.indexOf(charOfFirstStone) != -1) {
                    result++;
                }
            }
            if (result == stonesToList.size() - 1) {
                gems++;
            }
        }
        return gems;
    }

    /**
     * Removes duplicate characters from string.
     * @param string source string
     * @return string with unique characters
     */
    public String removeDuplicateCharacters(String string) {
        Set<Character> characterSet = new HashSet<>();
        char[] charArrayInput = string.toCharArray();
        for (Character character : charArrayInput) {
            characterSet.add(character);
        }
        Object[] charArrayOutput = characterSet.toArray();
        StringBuilder result = new StringBuilder();
        for (Object obj : charArrayOutput) {
            result.append(obj);
        }
        return result.toString();
    }
}
