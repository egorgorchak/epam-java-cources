package com.epam.university.java.core.task019;

/*
 * Completed by Laptev Egor 21.09.2020
 * */

public class Task019Impl implements Task019 {

    @Override
    public void invokeAction(Robot robot, RobotCommand command) {
        if (robot == null) {
            throw new IllegalArgumentException();
        }
        robot.invokeAction(command);
    }

    @Override
    public boolean isOnStartPosition(Robot robot) {
        if (robot == null) {
            throw new IllegalArgumentException();
        }
        return robot.getPosition().getX() == 0 && robot.getPosition().getY() == 0;
    }
}
