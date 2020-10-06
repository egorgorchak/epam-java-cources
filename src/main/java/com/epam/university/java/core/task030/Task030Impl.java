package com.epam.university.java.core.task030;
/*
 * Completed by Laptev Egor 06.10.2020
 * */

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Task030Impl implements Task030 {
    @Override
    public int daysBetweenDates(LocalDate dateStart, LocalDate dateEnd) {
        if (dateStart == null || dateEnd == null) {
            throw new IllegalArgumentException();
        }
        return Period.between(dateStart, dateEnd).getDays();
    }

    @Override
    public LocalDate adjustDays(LocalDate dateStart, int daysToAdd) {
        if (dateStart == null) {
            throw new IllegalArgumentException();
        }
        return dateStart.plus(Period.ofDays(daysToAdd));
    }

    @Override
    public long distanceBetween(Instant instantStart, Instant instantEnd) {
        if (instantStart == null || instantEnd == null) {
            throw new IllegalArgumentException();
        }
        return Duration.between(instantStart, instantEnd).getSeconds();
    }

    @Override
    public DayOfWeek getDayOfWeek(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException();
        }
        return localDate.getDayOfWeek();
    }

    @Override
    public LocalDate getNextWeekend(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException();
        }
        int daysToWeekend = localDate.getDayOfWeek().getValue();
        return localDate.plus(Period.ofDays(daysToWeekend));
    }

    @Override
    public LocalTime getLocalTime(String timeString) {
        if (timeString == null) {
            throw new IllegalArgumentException();
        }
        return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("hh:mma"));
    }
}
