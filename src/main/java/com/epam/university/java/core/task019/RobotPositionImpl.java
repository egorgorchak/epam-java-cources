package com.epam.university.java.core.task019;

public class RobotPositionImpl implements RobotPosition {
    private int x = 0;
    private int y = 0;
    private int angle = 0;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    public int getAngle() {
        return angle;
    }

    /**
     * Angle of rotation.
     * @param angle angle in degrees
     */
    public void setAngle(int angle) {
        this.angle = angle;
        if (this.angle == 360) {
            this.angle = 0;
        } else if (this.angle == -90) {
            this.angle = 270;
        }
    }
}
