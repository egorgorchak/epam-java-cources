package com.epam.university.java.core.task042;
/*
 * Completed by Laptev Egor 14.10.2020
 * */

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Task042Impl implements Task042 {

    @Override
    public BookingResponse checkAvailability(List<String> schedule, String currentTime) {
        if (schedule == null || currentTime == null) {
            throw new IllegalArgumentException();
        }

        LocalTime startOfTheDay = LocalTime.of(9,0);
        LocalTime endOfTheDay = LocalTime.of(18,0);
        LocalTime currentTimeParsed = LocalTime.parse(currentTime);

        if (schedule.isEmpty()) {
            if (currentTimeParsed.isBefore(startOfTheDay)) {
                TimeProposalResponse timeProposalResponse = new TimeProposalResponse();
                timeProposalResponse.setProposedTime(startOfTheDay.toString());
                return timeProposalResponse;
            } else if (currentTimeParsed.isAfter(endOfTheDay)
                    || currentTimeParsed.equals(endOfTheDay)) {
                return new BusyResponse();
            } else {
                return new AvailableResponse();
            }
        }

        ArrayList<LocalTime> freeTime = new ArrayList<>();

        for (int i = 0; i < schedule.size() - 1; i++) {
            String[] duration1 = schedule.get(i).split("-");
            LocalTime currentTime1 = LocalTime.parse(duration1[0]);
            LocalTime currentTime2 = LocalTime.parse(duration1[1]);

            if (i == 0 && currentTime1.isAfter(startOfTheDay)) {
                freeTime.add(startOfTheDay);
                freeTime.add(currentTime1);
            }

            String[] duration2 = schedule.get(i + 1).split("-");
            LocalTime nextTime1 = LocalTime.parse(duration2[0]);
            LocalTime nextTime2 = LocalTime.parse(duration2[1]);

            if (!currentTime2.equals(nextTime1)) {
                freeTime.add(currentTime2);
                freeTime.add(nextTime1);
            }
            if (i == schedule.size() - 2 && nextTime2.isBefore(endOfTheDay)) {
                freeTime.add(nextTime2);
                freeTime.add(endOfTheDay);
            }
        }

        if (freeTime.size() == 0) {
            return new BusyResponse();
        }

        for (int i = 0; i < freeTime.size() - 1; i += 2) {
            LocalTime freeTime1 = freeTime.get(i);
            LocalTime freeTime2 = freeTime.get(i + 1);

            if (freeTime1.isBefore(currentTimeParsed) && freeTime2.isAfter(currentTimeParsed)) {
                return new AvailableResponse();
            }
        }

        for (int i = 0; i < freeTime.size() - 1; i += 2) {
            LocalTime nextFreeTime = freeTime.get(i);
            LocalTime proposedTime;
            if (nextFreeTime.isAfter(currentTimeParsed)) {
                TimeProposalResponse timeProposalResponse = new TimeProposalResponse();
                timeProposalResponse.setProposedTime(nextFreeTime.toString());
                return timeProposalResponse;
            }
        }

        return new BusyResponse();
    }
}
