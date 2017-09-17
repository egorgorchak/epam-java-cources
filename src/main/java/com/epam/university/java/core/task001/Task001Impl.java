package com.epam.university.java.core.task001;

/**
 * Created by Doomsday Device on 17.09.2017.
 * <p>
 *     Task001
 *     ...
 * </p>
 */
public class Task001Impl implements Task001 {
    @Override
    public double addition(String firstNumber, String secondNumber) {
        double result;
        if (firstNumber == null && secondNumber == null) {
            throw new IllegalArgumentException();
        }

        try {
            result = Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
        } catch (Exception e) {
            throw new NumberFormatException();
        }

        return result;
    }

    @Override
    public double subtraction(String firstNumber, String secondNumber) {
        double result;
        if (firstNumber == null && secondNumber == null) {
            throw new IllegalArgumentException();
        }

        try {
            result = Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
        } catch (Exception e) {
            throw new NumberFormatException();
        }

        return result;
    }

    @Override
    public double multiplication(String firstNumber, String secondNumber) {
        double result;
        if (firstNumber == null && secondNumber == null) {
            throw new IllegalArgumentException();
        }

        try {
            result = Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
        } catch (Exception e) {
            throw new NumberFormatException();
        }

        return result;
    }

    @Override
    public double division(String firstNumber, String secondNumber) {
        double result;
        if (firstNumber == null && secondNumber == null) {
            throw new IllegalArgumentException();
        }

        try {
            result = Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
        } catch (Exception e) {
            throw new NumberFormatException();
        }

        return result;
    }
}