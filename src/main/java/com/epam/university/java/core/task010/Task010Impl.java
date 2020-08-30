package com.epam.university.java.core.task010;

/*
 * Completed by Laptev Egor 30.08.2020
 * */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Task010Impl implements Task010 {
    @Override
    public Map<String, Integer> countWordNumbers(File source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }

        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(source)))) {
            String nextLine = reader.readLine().toLowerCase();
            words.addAll(Arrays.asList(nextLine.split("[ .,!?:]")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        words.removeIf(string -> string.isEmpty());

        Map<String, Integer> result = new HashMap<>();
        for (String string1 : words) {
            int freq = 0;
            for (String string2 : words) {
                if (string1.equals(string2)) {
                    ++freq;
                }
            }
            result.put(string1, freq);
        }

        return result;
    }
}
