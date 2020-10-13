package com.epam.university.java.core.task034;
/*
 * Completed by Laptev Egor 10.10.2020
 * */

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task034Impl implements Task034 {
    static PersonImpl person = new PersonImpl();

    @Override
    public Person readWithSaxParser(DefaultHandler handler, String filepath) {
        if (filepath == null || filepath.isEmpty()) {
            throw new IllegalArgumentException();
        }
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        SaxHandlerImpl saxHandler;
        try {
            parser = factory.newSAXParser();
            saxHandler = new SaxHandlerImpl();
            parser.parse("./src/main/resources" + filepath, saxHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person readWithJaxbParser(String filepath) {
        if (filepath == null || filepath.isEmpty()) {
            throw new IllegalArgumentException();
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(
                    PersonImpl.class,
                    PhoneNumberImpl.class
            );
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            person = (PersonImpl) unmarshaller.unmarshal(
                    new File("./src/main/resources" + filepath)
            );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person readWithStaxParser(XMLStreamReader streamReader) {
        if (streamReader == null) {
            throw new IllegalArgumentException();
        }
        int id = 0;
        String firstName = "";
        String lastName = "";
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = inputFactory.createXMLEventReader(streamReader);
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "person":
                            Attribute attribute = startElement
                                    .getAttributeByName(new QName("id"));
                            id = Integer.parseInt(attribute.getValue());
                            break;
                        case "first-name":
                            event = reader.nextEvent();
                            firstName = event.asCharacters().getData();
                            break;
                        case "last-name":
                            event = reader.nextEvent();
                            lastName = event.asCharacters().getData();
                            break;
                        case "person-phone":
                            event = reader.nextEvent();
                            phoneNumbers.add(new PhoneNumberImpl(event.asCharacters().getData()));
                            break;
                        default:
                            continue;
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals("person")) {
                        person.setId(id);
                        person.setFirstName(firstName);
                        person.setLastName(lastName);
                        person.setPhoneNumbers(phoneNumbers);
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return person;
    }
}
