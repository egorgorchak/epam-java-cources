package com.epam.university.java.core.task009;

/*
 * Completed by Laptev Egor 30.08.2020
 * */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Task009Impl implements Task009 {
    @Override
    public Collection<String> countWords(File sourceFile) {
        HashSet<String> uniqueWords = new HashSet<>();

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)))) {
            String nextLine = reader.readLine().toLowerCase();
            uniqueWords.addAll(Arrays.asList(nextLine.split("[ .,!?:]")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        uniqueWords.removeIf(string -> string.isEmpty());
        return uniqueWords;
    }
}
