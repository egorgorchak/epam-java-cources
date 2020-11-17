package com.epam.university.java.core.task036;
/*
 * Created by Laptev Egor 17.11.2020
 * */

import java.util.function.Function;

public class IntegratorImpl implements Integrator {
    @Override
    public double integrate(double left, double right, Function<Double, Double> function) {
        int intervals = 1000000;
        double intervalLength = (right - left) / intervals;
        double result = 0;
        for (double i = left; i <= right; i += intervalLength) {
            result += function.apply(i) * intervalLength;
        }
        return result;
    }
}
