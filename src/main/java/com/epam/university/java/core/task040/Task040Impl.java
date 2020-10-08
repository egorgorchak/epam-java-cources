package com.epam.university.java.core.task040;
/*
 * Completed by Laptev Egor 08.10.2020
 * */

import javax.print.attribute.IntegerSyntax;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Task040Impl implements Task040 {
    @Override
    public int countScore(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }
        char[] shoots = str.toCharArray();
        ArrayList<Character> characters = new ArrayList<>();
        for (char shoot : shoots) {
            if (shoot != ' ') {
                characters.add(shoot);
            }
        }
        int score = 0;
        int frameScore = 0;
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i) == 'X') {
                int current = 10;
                int next1int;
                int next2int;
                char next1 = characters.get(i + 1);
                char next2 = characters.get(i + 2);
                if (next1 == 'X') {
                    next1int = 10;
                } else if (next2 == 'X') {
                    next2int = 10;
                }



            }
        }
        return 0;
    }
}