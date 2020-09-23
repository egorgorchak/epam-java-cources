package com.epam.university.java.core.task018;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/*
 * Completed by Laptev Egor 24.09.2020
 * */

public class Task018Impl implements Task018 {
    @Override
    public boolean isAnnotationPresent(Object toCheck, Class<?> annotationToFind) {
        if (toCheck == null || annotationToFind == null) {
            throw new IllegalArgumentException();
        }
        Class<?> clazz = toCheck.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field fld : fields) {
            if (fld.isAnnotationPresent(annotationToFind.asSubclass(Annotation.class))) {
                return true;
            }
        }
        Method[] methods = clazz.getMethods();
        for (Method mtd : methods) {
            if (mtd.isAnnotationPresent(annotationToFind.asSubclass(Annotation.class))) {
                return true;
            }
            Parameter[] parameters = mtd.getParameters();
            for (Parameter prm : parameters) {
                if (prm.isAnnotationPresent(annotationToFind.asSubclass(Annotation.class))) {
                    return true;
                }
            }
        }
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> cnstr : constructors) {
            if (cnstr.isAnnotationPresent(annotationToFind.asSubclass(Annotation.class))) {
                return true;
            }
        }
        Package pack = clazz.getPackage();
        return clazz.isAnnotationPresent(annotationToFind.asSubclass(Annotation.class))
                || pack.isAnnotationPresent(annotationToFind.asSubclass(Annotation.class));
    }
}
