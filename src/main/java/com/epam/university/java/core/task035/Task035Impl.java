package com.epam.university.java.core.task035;
/*
 * Completed by Laptev Egor 11.10.2020
 * */

import com.epam.university.java.core.task034.Person;
import com.epam.university.java.core.task034.PersonImpl;
import com.epam.university.java.core.task034.PhoneNumber;
import com.epam.university.java.core.task034.PhoneNumberImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        if (builder == null || jsonString == null || jsonString.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Person person = builder.create().fromJson(jsonString, PersonImpl.class);
        JsonArray array = builder
                .create()
                .fromJson(jsonString, JsonObject.class)
                .getAsJsonArray("phones");
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for (JsonElement element : array) {
            phoneNumbers.add(new PhoneNumberImpl(element.getAsString()));
        }
        person.setPhoneNumbers(phoneNumbers);
        return person;
    }
}
