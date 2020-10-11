package com.epam.university.java.core.task026;
/*
 * Completed by Laptev Egor 05.10.2020
 * */

public class Task026Impl implements Task026 {

    @Override
    public String encrypt(String sourceString, int shift) {
        if (sourceString == null || sourceString.isEmpty()) {
            throw new IllegalArgumentException();
        }
        StringBuilder encryptedString = new StringBuilder();
        while (shift > 26) {
            shift -= 26;
        }
        for (int i = 0; i < sourceString.length(); i++) {
            int currentChar = sourceString.charAt(i);
            if ((currentChar < 65 || currentChar > 90) && (currentChar < 97 || currentChar > 122)) {
                encryptedString.append((char) (currentChar));
                continue;
            }

            int encryptedChar;
            if (currentChar > 64 && currentChar < 91) {
                encryptedChar = currentChar + shift;
                if (encryptedChar > 90) {
                    encryptedChar -= 26;
                }
                encryptedString.append((char) (encryptedChar));
            } else if (currentChar > 96 && currentChar < 123) {
                encryptedChar = currentChar + shift;
                if (encryptedChar > 122) {
                    encryptedChar -= 26;
                }
                encryptedString.append((char) (encryptedChar));
            }
        }
        return encryptedString.toString();
    }

    @Override
    public String decrypt(String encryptedString, int shift) {
        if (encryptedString == null || encryptedString.isEmpty()) {
            throw new IllegalArgumentException();
        }
        while (shift > 26) {
            shift -= 26;
        }
        StringBuilder decryptedString = new StringBuilder();
        for (int i = 0; i < encryptedString.length(); i++) {
            int currentChar = encryptedString.charAt(i);
            if ((currentChar < 65 || currentChar > 90) && (currentChar < 97 || currentChar > 122)) {
                decryptedString.append((char) (currentChar));
                continue;
            }
            int encryptChar;
            if (currentChar > 64 && currentChar < 91) {
                encryptChar = currentChar - shift;
                if (encryptChar < 65) {
                    encryptChar += 26;
                }
                decryptedString.append((char) (encryptChar));
            } else if (currentChar > 96 && currentChar < 123) {
                encryptChar = currentChar - shift;
                if (encryptChar < 97) {
                    encryptChar += 26;
                }
                decryptedString.append((char) (encryptChar));
            }
        }
        return decryptedString.toString();
    }
}
