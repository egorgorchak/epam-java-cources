package com.epam.university.java.core.task037;
/*
 * Completed by Laptev Egor 14.10.2020
 * */

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

public class Task037Impl implements Task037 {
    public static volatile ArrayList<String> clocks = new ArrayList<>();

    @Override
    public Collection<String> switcher(Callable<String> ticker, Callable<String> tacker) {
        if (ticker == null || tacker == null) {
            throw new IllegalArgumentException();
        }
        Thread forTicker = null;
        Thread forTacker = null;
        for (int i = 0; i < 5; i++) {
            forTicker = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        clocks.add(ticker.call());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            forTacker = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        clocks.add(tacker.call());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            try {
                forTicker.start();
                forTicker.join();
                forTacker.start();
                forTacker.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return clocks;
    }
}
