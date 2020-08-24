package com.epam.university.java.core.task002;

/*
 * Completed by Laptev Egor 22.08.2020
 * Oh, shit, new tests...Fix it 24.08.2020
 * */

public class Task002Impl implements Task002 {
    @Override
    public boolean isEquals(String firstString, String secondString) {
        if (firstString == null || secondString == null) {
            throw new IllegalArgumentException();
        }
        return firstString.equals(secondString);
    }

    @Override
    public String left(String sourceString, int number) {
        if (sourceString == null || number < 0) {
            throw new IllegalArgumentException();
        } else if (number >= sourceString.length()) {
            return sourceString;
        } else {
            return sourceString.substring(0, number);
        }
    }

    @Override
    public String left(String sourceString, String separator) {
        if (sourceString == null || separator == null) {
            throw new IllegalArgumentException();
        }
        String[] result = sourceString.split(separator);
        return result[0];
    }

    @Override
    public String right(String sourceString, int number) {
        if (sourceString == null || number < 0) {
            throw new IllegalArgumentException();
        } else if (number >= sourceString.length()) {
            return sourceString;
        } else {
            return sourceString.substring(sourceString.length() - number);
        }
    }

    @Override
    public String right(String sourceString, String separator) {
        if (sourceString == null || separator == null) {
            throw new IllegalArgumentException();
        }
        String[] result = sourceString.split(separator);
        return result.length > 1 ? result[1] : sourceString;
    }

    @Override
    public String[] split(String sourceString, String split) {
        if (sourceString == null || split == null) {
            throw new IllegalArgumentException();
        }
        return sourceString.split(split);
    }

    @Override
    public String join(String[] sourceCollection, String glue) {
        if (sourceCollection == null || glue == null || sourceCollection.length == 0) {
            throw new IllegalArgumentException();
        }
        for (String string : sourceCollection) {
            if (string == null) {
                throw new IllegalArgumentException();
            }
        }
        return String.join(glue, sourceCollection);
    }
}
