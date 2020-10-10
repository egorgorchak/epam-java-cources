package com.epam.university.java.core.task034;
/*
 * Completed by Laptev Egor 10.10.2020
 * */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.Collection;

public class SaxHandlerImpl extends SaxHandler {
    private Person person = Task034Impl.person;
    private String elementName = "";
    private String firstName = "";
    private String lastName = "";
    private Collection<PhoneNumber> phoneNumbers = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementName = qName;
        if (elementName.equals("person")) {
            String id = attributes.getValue("id");
            Task034Impl.person.setId(Integer.parseInt(id));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String elementData = new String(ch, start, length).replace("\n", "");

        if (!elementData.isEmpty() && !elementData.isBlank()) {
            if (elementName.equals("first-name")) {
                firstName = elementData;
            } else if (elementName.equals("last-name")) {
                lastName = elementData;
            } else if (elementName.equals("person-phone")) {
                PhoneNumberImpl phoneNumber = new PhoneNumberImpl();
                phoneNumber.setPhoneNumber(elementData);
                phoneNumbers.add(phoneNumber);
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            person.setFirstName(firstName);
            person.setLastName(lastName);
            firstName = "";
            lastName = "";
        }
    }

    @Override
    public void endDocument() throws SAXException {
        person.setPhoneNumbers(phoneNumbers);
    }
}
