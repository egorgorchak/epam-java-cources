package com.epam.university.java.core.task031;
/*
 * Created by Laptev Egor 12.11.2020
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerImpl implements Server {
    private ServerSocket serverSocket;
    private LinkedList<String> messages = new LinkedList<>();
    private boolean isRun = true;

    @Override
    public String readMessage() {
        if (messages.isEmpty()) {
            return "";
        }
        return messages.pollLast();
    }

    @Override
    public void start() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(9999);
                while (!serverSocket.isClosed()) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(() -> {
                        try (BufferedReader in = new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream())
                        )) {
                            while (isRun) {
                                if (in.ready()) {
                                    messages.addLast(in.readLine());
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void stop() {
        isRun = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
