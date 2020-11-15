package com.epam.university.java.core.task062;
/*
 * Created by Laptev Egor 15.11.2020
 * */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Task062Impl implements Task062 {
    @Override
    public OutputStream objectSerialization(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }

    @Override
    public Object objectDeserialization(OutputStream outStream) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                ((ByteArrayOutputStream) outStream).toByteArray()
        );
        Object o = null;
        try {
            ObjectInputStream objectOutputStream = new ObjectInputStream(byteArrayInputStream);
            o = objectOutputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }
}
