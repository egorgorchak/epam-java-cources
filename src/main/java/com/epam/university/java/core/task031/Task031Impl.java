package com.epam.university.java.core.task031;
/*
 * Completed by Laptev Egor 11.10.2020
 * */

public class Task031Impl implements Task031 {

    @Override
    public Client createClient() {
        return new ClientImpl();
    }

    @Override
    public Server createServer() {
        return new ServerImpl();
    }
}
