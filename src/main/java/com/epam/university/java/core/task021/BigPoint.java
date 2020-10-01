package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;

import java.math.BigDecimal;

public class BigPoint implements Point {
    private BigDecimal x;
    private BigDecimal y;

    public BigPoint(double x, double y) {
        this.x = BigDecimal.valueOf(x);
        this.y = BigDecimal.valueOf(y);
    }

    public BigPoint(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x.doubleValue();
    }

    public BigDecimal getBigDecimalX() {
        return x;
    }

    @Override
    public double getY() {
        return y.doubleValue();
    }

    public BigDecimal getBigDecimalY() {
        return y;
    }

    @Override
    public void setX(double x) {
        this.x = BigDecimal.valueOf(x);
    }

    @Override
    public void setY(double y) {
        this.y = BigDecimal.valueOf(y);
    }
}
