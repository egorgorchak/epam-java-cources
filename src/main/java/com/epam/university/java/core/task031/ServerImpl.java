package com.epam.university.java.core.task031;
/*
 * Completed by Laptev Egor 11.10.2020
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;

public class ServerImpl implements Server {
    private static volatile ArrayDeque<String> messages;
    private ServerSocket serverSocket;
    private boolean isServerRunning;
    private boolean isClientRunning;

    public ServerImpl() {
        messages = new ArrayDeque<>();
    }

    @Override
    public void start() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(9999);
                    isServerRunning = true;
                    while (isServerRunning) {
                        Socket socket = serverSocket.accept();
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                isClientRunning = true;
                                try (BufferedReader input = new BufferedReader(
                                        new InputStreamReader(socket.getInputStream()))) {
                                    while (isClientRunning) {
                                        if (input.ready()) {
                                            String str = input.readLine();
                                            messages.push(str);
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                    }
                } catch (IOException e) {
                    System.out.println("Socket is closed");
                }
            }
        });
        thread1.start();
    }

    @Override
    public void stop() {
        isServerRunning = false;
        isClientRunning = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readMessage() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (messages.size() == 0) {
            return "";
        }
        return messages.poll();
    }
}
