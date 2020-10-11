package com.epam.university.java.core.task027;
/*
 * Completed by Laptev Egor 05.10.2020
 * */

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class Task027Impl implements Task027 {
    @Override
    public Collection<Integer> extract(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        if (sourceString.startsWith("0")) {
            return new ArrayList<>();
        }
        Set<Integer> result = new LinkedHashSet<>();
        //делим строку на массив символов, парсим и записываем в integersFromSource
        ArrayList<Integer> integersFromSource = new ArrayList<>();
        for (int i = 0; i < sourceString.length(); i++) {
            integersFromSource.add(Integer.parseInt(String.valueOf(sourceString.charAt(i))));
        }

        ArrayList<String> symbols = new ArrayList<>();

        int digits = 1;
        boolean isCorrect = false;
        StringBuilder sym;
        while (!isCorrect) {
            isCorrect = true;
            int toDelete = 0; //вместо removeRange()
            //наполняем массив symbols элементами, с поочередной переборкой количества digits
            for (int i = 0; i < integersFromSource.size(); i = i + digits) {
                sym = new StringBuilder();
                for (int j = i; j < digits + i && j < integersFromSource.size(); j++) {
                    sym.append(integersFromSource.get(j));
                }
                symbols.add((sym.toString()));
            }

            //проверяем, удовлетворяет ли условию задачи последовательность из symbols
            for (int i = 0; i < symbols.size() - 1; i++) {
                int currentChar = Integer.parseInt(symbols.get(i));
                int nextChar = Integer.parseInt(symbols.get(i + 1));

                if (nextChar - currentChar != 1) {
                    symbols.clear();
                    digits++;
                    isCorrect = false;
                    //проверка удовлетворяет ли currentChar и nextChar требованиям задачи
                    //условно: 99 + 1 == 100? тогда добавляем в result
                    int check = currentChar + 1;
                    if (String.valueOf(check).length() != String.valueOf(currentChar).length()
                            && String.valueOf(nextChar).startsWith("1")) {
                        result.add(currentChar);
                    }
                } else {
                    //вот почему Set, а не Array
                    result.add(currentChar);
                    result.add(nextChar);
                }
            }

            //когда остается один элемент массива (цикл выше не срабатывает)
            if (symbols.size() == 1) {
                ArrayList<Integer> resultIntegers = new ArrayList<>(result);
                int integer1;
                if (!result.isEmpty()) {
                    integer1 = resultIntegers.get(resultIntegers.size() - 1);
                } else {
                    break;
                }
                int integer2 = Integer.parseInt(symbols.get(0));
                if (integer2 - integer1 == 1) {
                    result.add(integer2);
                }
            }

            //подсчитываем сколько символов длиной элементы в result
            //и удаляем их из integersFromSource чтобы не мешались
            if (!result.isEmpty()) {
                toDelete = symbolsAmount(result);
                if (integersFromSource.size() > toDelete) {
                    integersFromSource.subList(0, toDelete).clear();
                }
            }
        }
        if (symbolsAmount(result) == sourceString.length()) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * The method counts the number of characters in elements of specified collection.
     * @param collection collection of elements
     * @return number of characters
     */
    public int symbolsAmount(Collection<Integer> collection) {
        int amount = 0;
        for (Integer integer : collection) {
            amount += String.valueOf(integer).length();
        }
        return amount;
    }
}
