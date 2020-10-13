package com.epam.university.java.core.task031;
/*
 * Completed by Laptev Egor 11.10.2020
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerImpl implements Server {
    private Socket socket;
    private ServerSocket serverSocket;
    private BufferedReader input;
    private boolean isServerRunning;

    public ServerImpl() {
        System.out.println("Server is created...");
    }

    @Override
    public void start() {
        isServerRunning = true;
        System.out.println("Server is started. Thread " + Thread.currentThread().getName());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(9999);
                    while (isServerRunning) {
                        socket = serverSocket.accept();
                        System.out.println("Establish the connection with new Client. Thread " + Thread.currentThread().getName());
                        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        ClientHandler clientHandler = new ClientHandler(socket, input);
                        clientHandler.start();
                    }
                } catch (IOException e) {
                    System.out.println("Error starting the server");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void stop() {
        try {
            System.out.println("Server is stopped...");
            isServerRunning = false;
            socket.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readMessage() {
        System.out.println("Server is read the message...");
        String msg = "";
        try {
            if (input.ready()) {
                msg = input.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader clientInput;

        public ClientHandler(Socket clientSocket, BufferedReader clientInput) {
            this.clientSocket = clientSocket;
            this.clientInput = clientInput;
        }

        @Override
        public void run() {

        }

        public String clientMessage() {
            return null;
        }
    }
}


