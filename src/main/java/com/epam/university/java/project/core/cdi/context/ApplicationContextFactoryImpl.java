package com.epam.university.java.project.core.cdi.context;
/*
 * Created by Laptev Egor 20.10.2020
 * */

public class ApplicationContextFactoryImpl implements ApplicationContextFactory {
    @Override
    public ApplicationContext newInstance() {
        return new ApplicationContextImpl();
    }
}
