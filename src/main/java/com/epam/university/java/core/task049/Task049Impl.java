package com.epam.university.java.core.task049;
/*
 * Completed by Laptev Egor 14.10.2020
 * */

public class Task049Impl implements Task049 {

    @Override
    public String getResultList(String first, String second) {
        if (first == null || second == null
                || first.isBlank() || second.isBlank()
                || first.isEmpty() || second.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int length1 = first.length();
        int length2 = second.length();

        int[][] table = new int[length1][length2];
        String common = "";
        int len = 0;
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    if (i == 0 || j == 0) {
                        table[i][j] = 1;
                    } else {
                        table[i][j] = 1 + table[i - 1][j - 1];
                    }
                    if (table[i][j] > len) {
                        len = table[i][j];
                        common = "";
                    }
                    if (table[i][j] == len) {
                        common = first.substring(i - len + 1, i + 1);
                    }
                }
            }
        }
        return common;
    }
}
