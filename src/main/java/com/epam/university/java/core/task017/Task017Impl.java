package com.epam.university.java.core.task017;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

/*
 * Completed by Laptev Egor 20.09.2020
 * */

public class Task017Impl implements Task017 {
    @Override
    public String formatString(Object... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        for (Object obj : args) {
            if (obj == null) {
                throw new IllegalArgumentException();
            }
        }
        StringBuilder string = new StringBuilder("You know ");
        string.append(args[0])
                .append(", ")
                .append(args[1])
                .append("!");
        return string.toString();
    }

    @Override
    public String formatNumbers(Object... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
        dfs.setDecimalSeparator('.');

        DecimalFormat df1 = new DecimalFormat("#.0", dfs);
        DecimalFormat df2 = new DecimalFormat("#.00", dfs);
        DecimalFormat df3 = new DecimalFormat("+#.00", dfs);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(df1.format(args[0]))
                .append(", ")
                .append(df2.format(args[0]))
                .append(", ")
                .append(df3.format(args[0]))
                .append(", ")
                .append(Double.toHexString((Double) args[0]));
        return stringBuilder.toString();
    }

    @Override
    public String formatDates(Object... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.dd.MM");
        return sdf.format(args[0]);
    }
}
