package com.epam.university.java.core.task028;
/*
 * Completed by Laptev Egor 06.10.2020
 * */

public class Task028Impl implements Task028 {
    private int ways;

    @Override
    public int getWays(int value, int power) {
        findWays(value, power, 0, 1);
        return ways;
    }

    /**
     * Find the number of ways for given value and number.
     * @param value positive integer value
     * @param power power for number
     * @param sum current sum
     * @param number current number
     */
    public void findWays(int value, int power, int sum, int number) {
        int powNumber = (int) Math.pow(number, power);
        if (powNumber <= value - sum) {
            findWays(value, power, sum, number + 1);
            sum = sum + powNumber;
            if (sum == value) {
                ways++;
            } else {
                findWays(value, power, sum, number + 1);
            }
        }
    }
}