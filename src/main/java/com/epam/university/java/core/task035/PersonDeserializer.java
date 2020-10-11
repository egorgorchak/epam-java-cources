package com.epam.university.java.core.task035;
/*
 * Completed by Laptev Egor 11.10.2020
 * */

import com.epam.university.java.core.task034.Person;
import com.epam.university.java.core.task034.PersonImpl;
import com.epam.university.java.core.task034.PhoneNumber;
import com.epam.university.java.core.task034.PhoneNumberImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;

public class PersonDeserializer extends StdDeserializer<Person> {
    public PersonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Person deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Person person = new PersonImpl();
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        while (!p.isClosed()) {
            JsonToken jsonToken = p.nextToken();
            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = p.getCurrentName();
                p.nextToken();
                if (fieldName.equals("id")) {
                    person.setId(p.getValueAsInt());
                } else if (fieldName.equals("firstName")) {
                    person.setFirstName(p.getValueAsString());
                } else if (fieldName.equals("lastName")) {
                    person.setLastName(p.getValueAsString());
                } if (fieldName.equals("phones")) {
                    while (!JsonToken.END_ARRAY.equals(jsonToken)) {
                        jsonToken = p.nextToken();
                        if (JsonToken.VALUE_STRING.equals(jsonToken)) {
                            String arrayElement = p.getValueAsString();
                            phoneNumbers.add(new PhoneNumberImpl(arrayElement));
                        }
                    }
                }
            }
        }
        person.setPhoneNumbers(phoneNumbers);
        return person;
    }
}
