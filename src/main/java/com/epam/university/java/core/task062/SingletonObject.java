package com.epam.university.java.core.task062;

import java.io.Serializable;

public class SingletonObject implements Serializable {
    private static SingletonObject instance;

    private SingletonObject() {
    }

    /**
     * Return or create (if not created yet) a singleton instance.
     *
     * @return singleton instance
     */
    public static SingletonObject getInstance() {
        if (instance == null) {
            instance = new SingletonObject();
        }
        return instance;
    }

    private Object readResolve() {
        return getInstance();
    }
}
