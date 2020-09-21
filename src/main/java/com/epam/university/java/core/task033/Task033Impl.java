package com.epam.university.java.core.task033;

/*
 * Completed by Laptev Egor 21.09.2020
 * */

public class Task033Impl implements Task033 {
    @Override
    public void doSomething(int first, int second) {
        if (first == 0 && second == 0) {
            throw new ArithmeticException();
        } else if (first > second) {
            Exception e = new BaseExceptionImpl();
            throw new GreaterExceptionImpl("First > Second", e);
        } else if (first < second) {
            Exception e = new BaseExceptionImpl();
            throw new LessExceptionImpl("Second > First", e);
        }
    }
}
