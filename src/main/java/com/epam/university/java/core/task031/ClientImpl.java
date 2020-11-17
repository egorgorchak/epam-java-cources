package com.epam.university.java.core.task031;
/*
 * Created by Laptev Egor 12.11.2020
 * */

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientImpl implements Client {
    private Socket socket;
    private BufferedWriter out;
    private boolean isMsgNull;

    @Override
    public void sendMessage(String message) {
        if (message == null) {
            isMsgNull = true;
        }
        try {
            out.write(message);
            out.newLine();
            out.flush();
            TimeUnit.MILLISECONDS.sleep(150);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 7575);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            out.close();
            socket.close();
            if (isMsgNull) {
                throw new IllegalArgumentException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
