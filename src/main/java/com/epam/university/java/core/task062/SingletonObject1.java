package com.epam.university.java.core.task062;

import java.io.Serializable;

public class SingletonObject1 implements Serializable {
    private static SingletonObject1 instance;

    private SingletonObject1() {
    }

    /**
     * Return or create (if not created yet) a singleton instance.
     *
     * @return singleton instance
     */
    public static SingletonObject1 getInstance() {
        if (instance == null) {
            instance = new SingletonObject1();
        }
        return instance;
    }

    private Object readResolve() {
        return getInstance();
    }
}
