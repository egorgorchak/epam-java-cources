package com.epam.university.java.core.task062;

import java.io.Serializable;

public class SingletonObjectImpl implements Serializable, SingletonObject {
    private static SingletonObjectImpl instance;

    private SingletonObjectImpl() {
    }

    /**
     * Return or create (if not created yet) a singleton instance.
     *
     * @return singleton instance
     */
    public static SingletonObjectImpl getInstance() {
        if (instance == null) {
            instance = new SingletonObjectImpl();
        }
        return instance;
    }

    private Object readResolve() {
        return getInstance();
    }
}
