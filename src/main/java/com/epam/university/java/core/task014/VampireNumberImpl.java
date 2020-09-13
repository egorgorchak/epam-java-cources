package com.epam.university.java.core.task014;

public class VampireNumberImpl implements VampireNumber {
    private int first;
    private int second;
    private int multiplication;

    /**
     * Class constructor.
     * @param first first part of vampire number
     * @param second second part of vampire number
     * @param multiplication multiplication of first and second parameters
     */
    public VampireNumberImpl(int first, int second, int multiplication) {
        this.first = first;
        this.second = second;
        this.multiplication = multiplication;
    }

    @Override
    public int getMultiplication() {
        return this.multiplication;
    }

    @Override
    public int getFirst() {
        return this.first;
    }

    @Override
    public int getSecond() {
        return this.second;
    }

    @Override
    public boolean equals(Object obj) {
        VampireNumber vampireNumber = (VampireNumber) obj;
        return this.multiplication == vampireNumber.getMultiplication();
    }
}
