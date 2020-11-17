package com.epam.university.java.core.task036;
/*
 * Created by Laptev Egor 17.11.2020
 * */

import java.util.function.Function;

public class Task036Impl implements Task036 {
    @Override
    public double integrate(
            Function<Double, Double> function,
            Integrator integrator,
            double limitLeft,
            double limitRight
    ) {
        return integrator.integrate(limitLeft, limitRight, function);
    }
}
