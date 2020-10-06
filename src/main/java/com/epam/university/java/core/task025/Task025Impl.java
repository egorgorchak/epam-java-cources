package com.epam.university.java.core.task025;
/*
 * Completed by Laptev Egor 06.10.2020
 * */

public class Task025Impl implements Task025 {
    @Override
    public int getAmountOfAlteredLetters(String sourceMessage) {
        if (sourceMessage == null) {
            throw new IllegalArgumentException();
        }
        String sos = "SOS";
        StringBuilder rightOrder = new StringBuilder();
        for (int i = 0, j = 0; i < sourceMessage.length(); i++, j++) {
            rightOrder.append(sos.charAt(j));
            if (j == 2) {
                j = -1;
            }
        }
        int count = 0;
        for (int i = 0; i < sourceMessage.length(); i++) {
            char charFromMessage = sourceMessage.charAt(i);
            char charFromRightOrder = rightOrder.charAt(i);

            if (charFromMessage != charFromRightOrder) {
                count++;
            }
        }
        return count;
    }
}
