package com.epam.university.java.project.service;
/*
 * Created by Laptev Egor 30.10.2020
 * */

import com.epam.university.java.project.core.cdi.impl.io.XmlResource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookStatus;

import java.time.LocalDate;
import java.util.Collection;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    private StateMachineManager stateMachineManager;
    private XmlResource xmlResource;

    {
        xmlResource = new XmlResource(
                "./src/main/resources/project/DefaultBookStateMachineDefinition.xml"
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public Book createBook() {
        StateMachineDefinition<BookStatus, BookEvent> stateMachineDefinition =
                (StateMachineDefinition<BookStatus, BookEvent>) stateMachineManager
                        .loadDefinition(xmlResource);
        Book book = bookDao.createBook();
        book = (Book) stateMachineManager.initStateMachine(book, stateMachineDefinition);
        return (Book) stateMachineManager.handleEvent(book, BookEvent.CREATE);
    }

    @Override
    public Book getBook(int id) {
        return bookDao.getBook(id);
    }

    @Override
    public Collection<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public void remove(Book book) {
        bookDao.remove(book);
    }

    @Override
    public Book save(Book book) {
        bookDao.save(book);
        return book;
    }

    @Override
    public Book accept(Book book, String number) {
        book.setSerialNumber(number);
        stateMachineManager.handleEvent(book, BookEvent.ACCEPT);
        return book;
    }

    @Override
    public Book issue(Book book, LocalDate returnDate) {
        book.setReturnDate(returnDate);
        stateMachineManager.handleEvent(book, BookEvent.ISSUE);
        return book;
    }

    @Override
    public Book returnFromIssue(Book book) {
        stateMachineManager.handleEvent(book, BookEvent.RETURN);
        return book;
    }
}
