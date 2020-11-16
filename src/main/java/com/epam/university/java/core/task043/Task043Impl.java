package com.epam.university.java.core.task043;
/*
 * Completed by Laptev Egor 06.10.2020
 * */

import java.util.HashMap;
import java.util.Map;

public class Task043Impl implements Task043 {
    private HashMap<Character, String> morse = new HashMap<Character, String>();

    {
        morse.put('A', ".-");
        morse.put('B', "-...");
        morse.put('C', "---.");
        morse.put('D', "-..");
        morse.put('E', ".");
        morse.put('F', "..-.");
        morse.put('G', "--.");
        morse.put('H', "....");
        morse.put('I', "..");
        morse.put('J', ".---");
        morse.put('K', "-.-");
        morse.put('L', ".-..");
        morse.put('M', "----");
        morse.put('N', "-.");
        morse.put('O', "---");
        morse.put('P', ".--.");
        morse.put('Q', "--.-");
        morse.put('R', ".-.");
        morse.put('S', "...");
        morse.put('T', "-");
        morse.put('U', "..-");
        morse.put('V', "...-");
        morse.put('W', ".--");
        morse.put('X', "-..-");
        morse.put('Y', "-.--");
        morse.put('Z', "--..");
        morse.put(',', "--..--");
        morse.put('1', ".----");
        morse.put('2', "..---");
        morse.put('3', "...--");
        morse.put('4', "....-");
        morse.put('5', ".....");
        morse.put('6', "-....");
        morse.put('7', "--...");
        morse.put('8', "---..");
        morse.put('9', "----.");
        morse.put('0', "-----");
    }

    @Override
    public String encode(String sourceString) { //hey jude
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sourceString.length(); i++) {
            char current = sourceString.charAt(i);
            if (current == ' ') {
                result.append(current);
                result.append(current);
            } else {
                result.append(morse.get(current));
                result.append(" ");
            }
        }
        return result.toString().trim();
    }

    @Override
    public String decode(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder result = new StringBuilder();
        StringBuilder morseChar = new StringBuilder();
        String[] split = sourceString.split("   ");
        for (String str : split) {
            for (int i = 0; i < str.length(); i++) {
                char current = str.charAt(i);
                if (current != ' ' || i == str.length() - 1) {
                    morseChar.append(current);
                }
                if (i == str.length() - 1 || current == ' ') {
                    String forMap = morseChar.toString();
                    String decodingChar = morse
                            .entrySet()
                            .stream()
                            .filter(entry -> forMap.equals(entry.getValue()))
                            .map(Map.Entry::getKey)
                            .findFirst()
                            .get()
                            .toString();
                    result.append(decodingChar);
                    morseChar = new StringBuilder();
                }
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
}
