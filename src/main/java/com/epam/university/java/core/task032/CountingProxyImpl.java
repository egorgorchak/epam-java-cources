package com.epam.university.java.core.task032;
/*
 * Created by Laptev Egor 12.11.2020
 * */

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CountingProxyImpl implements CountingProxy {
    private final List<String> countList = new ArrayList<>();
    private final SomeActionExecutor sae = new SomeActionExecutorImpl();

    @Override
    public int getInvocationsCount(String methodName) {
        int result = 0;
        for (String s : countList) {
            if (s.equals(methodName)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        countList.add(method.getName());
        return method.invoke(sae);
    }
}
