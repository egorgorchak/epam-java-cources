package com.epam.university.java.core.task035;
/*
 * Completed by Laptev Egor 11.10.2020
 * */

import com.epam.university.java.core.task034.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class Task035Impl implements Task035 {
    @Override
    public Person readWithJackson(ObjectMapper mapper, String jsonString) {
        if (mapper == null || jsonString == null || jsonString.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Person person = null;
        try {
            SimpleModule module = new SimpleModule("PersonDeserializer");
            module.addDeserializer(Person.class, new PersonDeserializer(Person.class));
            mapper.registerModule(module);
            person = mapper.readValue(jsonString, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person readWithGson(GsonBuilder builder, String jsonString) {
        return null;
    }
}
