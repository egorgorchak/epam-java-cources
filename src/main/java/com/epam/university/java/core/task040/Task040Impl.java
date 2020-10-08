package com.epam.university.java.core.task040;
/*
 * Completed by Laptev Egor 08.10.2020
 * */

import java.util.ArrayList;

public class Task040Impl implements Task040 {
    @Override
    public int countScore(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }
        char[] charArray = str.toCharArray();
        ArrayList<String> shoots = new ArrayList<>();
        for (char shoot : charArray) {
            if (shoot != ' ') {
                if (shoot == 'X') {
                    shoots.add("10");
                } else {
                    shoots.add(String.valueOf(shoot));
                }
            }
        }
        int score = 0;

        for (int i = 0; i < shoots.size(); i++) {
            int frameScore = 0;
            if (shoots.get(i).equals("10")) {
                int current = Integer.parseInt(shoots.get(i));
                int next1 = Integer.parseInt(shoots.get(i + 1));

                String str2 = shoots.get(i + 2);
                int next2;
                if (str2.equals("/")) {
                    next2 = 10 - next1;
                } else {
                    next2 = Integer.parseInt(str2);
                }
                frameScore = current + next1 + next2;
                if (i == shoots.size() - 3) {
                    score += frameScore;
                    break;
                }
            } else if (shoots.get(i).equals("/")) {
                int previous = Integer.parseInt(shoots.get(i - 1));
                int current = 10 - previous;
                int next1 = Integer.parseInt(shoots.get(i + 1));
                frameScore = previous + current + next1;
            } else {
                int next1 = Integer.parseInt(shoots.get(i));
                String str1 = shoots.get(i + 1);
                if (str1.equals("/")) {
                    continue;
                }
                int next2 = Integer.parseInt(str1);
                frameScore = next1 + next2;
                i++;
            }
            score += frameScore;
        }
        return score;
    }
}