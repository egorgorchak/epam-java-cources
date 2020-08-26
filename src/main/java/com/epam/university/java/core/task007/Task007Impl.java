package com.epam.university.java.core.task007;

/*
 * Completed by Laptev Egor 26.08.2020
 * */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task007Impl implements Task007 {
    @Override
    public Collection<Integer> multiplyPolynomial(Collection<Integer> first, Collection<Integer> second) {
        List<Integer> firstPol = (List<Integer>) first;
        List<Integer> secondPol = (List<Integer>) second;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < (firstPol.size() + secondPol.size() - 1); i++) {
            result.add(0);
        }

        for (int i = 0; i < firstPol.size(); i++) {
            for (int j = 0; j < secondPol.size(); j++) {
                int curPos = (i + j);
                result.set(curPos, result.get(curPos) + firstPol.get(i) * secondPol.get(j));
            }
        }
        return result;
    }
}
