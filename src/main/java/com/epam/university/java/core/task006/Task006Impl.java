package com.epam.university.java.core.task006;

/*
 * Completed by Laptev Egor 26.08.2020
 * */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

public class Task006Impl implements Task006 {
    @Override
    public double resistance(Collection<Measurement> measurements) {
        if (measurements == null) {
            throw new IllegalArgumentException();
        } else if (measurements.size() == 0) {
            return 0;
        }
        double sumOfAmp = 0;
        double sumOfVolt = 0;
        double sumOfPowAmp = 0;
        double sumOfAmpNVolt = 0;

        for (Measurement measure : measurements) {
            sumOfAmp += measure.getAmperage();
            sumOfVolt += measure.getVoltage();
            sumOfPowAmp += Math.pow(measure.getAmperage(), 2);
            sumOfAmpNVolt += measure.getAmperage() * measure.getVoltage();
        }

        if (sumOfAmp == 0) {
            return 0;
        }

        double delta = sumOfPowAmp * measurements.size() - sumOfAmp * sumOfAmp;
        double factorA = (sumOfAmpNVolt * measurements.size() - sumOfVolt * sumOfAmp) / delta;
        BigDecimal resistance = new BigDecimal(factorA);
        resistance = resistance.setScale(3, RoundingMode.HALF_UP);

        return resistance.doubleValue();
    }
}
