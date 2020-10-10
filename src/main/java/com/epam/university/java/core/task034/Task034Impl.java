package com.epam.university.java.core.task034;
/*
 * Completed by Laptev Egor 10.10.2020
 * */

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.IOException;

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
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonImpl.class, PhoneNumberImpl.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            person = (PersonImpl) unmarshaller.unmarshal(new File("./src/main/resources" + filepath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person readWithStaxParser(XMLStreamReader streamReader) {
        return null;
    }
}
