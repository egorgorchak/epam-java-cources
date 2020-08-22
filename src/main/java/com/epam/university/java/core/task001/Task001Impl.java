package com.epam.university.java.core.task001;

/*
* Completed by Laptev Egor 18.08.2020
* */

public class Task001Impl implements Task001 {
    @Override
    public double addition(String firstNumber, String secondNumber) {
        if (firstNumber == null || secondNumber == null) {
            throw new IllegalArgumentException();
        }
        double first = Double.parseDouble(firstNumber);
        double second = Double.parseDouble(secondNumber);
        return first + second;
    }

    @Override
    public double subtraction(String firstNumber, String secondNumber) {
        if (firstNumber == null || secondNumber == null) {
            throw new IllegalArgumentException();
        }
        double first = Double.parseDouble(firstNumber);
        double second = Double.parseDouble(secondNumber);
        return first - second;
    }

    @Override
    public double multiplication(String firstNumber, String secondNumber) {
        if (firstNumber == null || secondNumber == null) {
            throw new IllegalArgumentException();
        }
        double first = Double.parseDouble(firstNumber);
        double second = Double.parseDouble(secondNumber);
        return first * second;
    }

    @Override
    public double division(String firstNumber, String secondNumber) {
        if (firstNumber == null || secondNumber == null) {
            throw new IllegalArgumentException();
        }
        double first = Double.parseDouble(firstNumber);
        double second = Double.parseDouble(secondNumber);
        return first / second;
    }
}
