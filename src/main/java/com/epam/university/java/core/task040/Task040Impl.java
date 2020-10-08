package com.epam.university.java.core.task040;
/*
 * Completed by Laptev Egor 08.10.2020
 * */

import java.util.ArrayList;
import java.util.List;

public class Task040Impl implements Task040 {
    boolean isStrike = false;
    boolean isSpare = false;

    @Override
    public int countScore(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String[] frames = str.split(" ");

        int score = 0;
        int frameScore = 0;

        for (int i = 0; i < frames.length; i++) {
            String currentFrame = frames[i];
            int[] shoots = countShoot(currentFrame);
            if (isStrike) {
                frameScore = frameScore + shoots[0];
                countShoot(frames[i + 1]);

            }
            score = score + frameScore;
        }

        return score;
    }

    public List<Integer> countShoot(String shoot) {
        ArrayList<Integer> shoots = new ArrayList<>();
        int firstThrow = 0;
        int secondThrow = 0;
        if (shoot.length() == 1) {
            firstThrow = 10;
            isStrike = true;
            return new int[] {firstThrow};
        } else if (shoot.contains("/")) {
            firstThrow = Integer.parseInt(String.valueOf(shoot.charAt(0)));
            secondThrow = 10 - firstThrow;
            isSpare = true;
            return new int[] {firstThrow, secondThrow};
        } else {
            firstThrow = Integer.parseInt(String.valueOf(shoot.charAt(0)));
            secondThrow = Integer.parseInt(String.valueOf(shoot.charAt(1)));
            return new int[] {firstThrow, secondThrow};
        }
    }
}
