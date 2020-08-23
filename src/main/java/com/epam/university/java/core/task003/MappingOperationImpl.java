package com.epam.university.java.core.task003;

public class MappingOperationImpl implements MappingOperation {
    @Override
    public String map(String source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        return new StringBuilder(source).reverse().toString();
    }
}
