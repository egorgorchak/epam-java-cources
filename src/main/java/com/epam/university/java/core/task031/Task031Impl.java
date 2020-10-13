package com.epam.university.java.core.task031;
/*
 * Completed by Laptev Egor 11.10.2020
 * */

public class Task031Impl implements Task031 {
    public static void main(String[] args) {
        Task031Impl task031 = new Task031Impl();
        Server server = task031.createServer();
        Client client = task031.createClient();

        server.start();
        client.start();

        String message1 = "Hello, world!";
        String message2 = "Winter is coming!";

        client.sendMessage(message1);
        client.sendMessage(message2);

        System.out.println(server.readMessage());
        System.out.println(server.readMessage());

        client.stop();
        server.stop();

    }

    @Override
    public Client createClient() {
        return new ClientImpl();
    }

    @Override
    public Server createServer() {
        return new ServerImpl();
    }
}
