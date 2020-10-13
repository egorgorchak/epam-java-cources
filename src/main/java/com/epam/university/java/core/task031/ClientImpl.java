package com.epam.university.java.core.task031;
/*
 * Completed by Laptev Egor 11.10.2020
 * */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientImpl implements Client {
    private Socket socket;
    private PrintWriter out;

    public ClientImpl() {
        System.out.println("Client is created...");
    }

    @Override
    public void start() {
        System.out.println("Client is started. Thread " + Thread.currentThread().getName());
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.out.println("Client is stopped...");
        try {
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Sending message...");
        out.println(message);
        out.flush();
    }
}
