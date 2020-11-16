package com.epam.university.java.core.task053;
/*
 * Created by Laptev Egor 02.11.2020
 * */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task053Impl implements Task053 {
    @Override
    public double calculate(String input) {
        if (input == null || input.length() < 3) {
            throw new IllegalArgumentException();
        }
        if (!Pattern.matches("[\\d+\\-*/^()]+", input)) {
            throw new IllegalArgumentException();
        }

        ArrayList<String> binOperations = new ArrayList<>(Arrays.asList("+", "-", "/", "*", "^"));

        Matcher divisionMatcher = Pattern.compile("\\d+|.").matcher(input);
        LinkedList<String> symbolsList = new LinkedList<>();
        while (divisionMatcher.find()) {
            int start = divisionMatcher.start();
            int end = divisionMatcher.end();
            symbolsList.add(input.substring(start, end));
        }

        LinkedList<String> operandStack = new LinkedList<>();
        LinkedList<String> symbolsStack = new LinkedList<>();

        while (symbolsList.size() > 0) {
            String firstChar = symbolsList.pollFirst();
            if (Pattern.matches("\\d+", firstChar)) {
                operandStack.add(firstChar);
            } else if (firstChar.equals("(")) {
                symbolsStack.add(firstChar);
            } else if (firstChar.equals(")")) {
                String nextChar;
                do {
                    nextChar = symbolsStack.pollLast();
                    if (nextChar == null) {
                        throw new IllegalArgumentException();
                    }
                    if (!nextChar.equals("(")) {
                        operandStack.add(nextChar);
                    }
                } while (!nextChar.equals("("));
            } else if (binOperations.contains(firstChar)) {
                if (symbolsStack.size() == 0) {
                    symbolsStack.add(firstChar);
                    continue;
                }
                int firstCharPriority = getPriority(firstChar);
                for (int i = symbolsStack.size() - 1; i >= 0; i--) {
                    String string = symbolsStack.get(i);
                    int lastStackSymPriority = getPriority(string);
                    if (lastStackSymPriority >= firstCharPriority) {
                        operandStack.add(symbolsStack.pollLast());
                    } else {
                        break;
                    }
                }
                symbolsStack.add(firstChar);
            }
        }

        while (symbolsStack.size() > 0) {
            operandStack.add(symbolsStack.pollLast());
        }

        LinkedList<Double> calculationStack = new LinkedList<>();
        do {
            String string = operandStack.pollFirst();
            if (string == null) {
                break;
            }
            if (Pattern.matches("\\d+", string)) {
                calculationStack.add(Double.parseDouble(string));
            } else {
                Double operand1 = calculationStack.pollLast();
                Double operand2 = calculationStack.pollLast();
                if (operand1 == null || operand2 == null) {
                    throw new RuntimeException();
                }
                switch (string) {
                    case "+":
                        calculationStack.add(operand2 + operand1);
                        break;
                    case "-":
                        calculationStack.add(operand2 - operand1);
                        break;
                    case "*":
                        calculationStack.add(operand2 * operand1);
                        break;
                    case "/":
                        calculationStack.add(operand2 / operand1);
                        break;
                    case "^":
                        calculationStack.add(Math.pow(operand2, operand1));
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        } while (calculationStack.size() >= 1);

        return calculationStack.getFirst();
    }

    /**
     * Determine the priority of operation.
     * @param c - operation
     * @return priority of operation
     */
    public int getPriority(String c) {
        switch (c) {
            case "(":
            case ")":
                return 0;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }
}
