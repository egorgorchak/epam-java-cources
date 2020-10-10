package com.epam.university.java.core.task034;
/*
 * Completed by Laptev Egor 10.10.2020
 * */

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;

public class Task034Impl implements Task034 {
    static Person person = new PersonImpl();

    @Override
    public Person readWithSaxParser(DefaultHandler handler, String filepath) {
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
        return null;
    }

    @Override
    public Person readWithStaxParser(XMLStreamReader streamReader) {
        return null;
    }
}
