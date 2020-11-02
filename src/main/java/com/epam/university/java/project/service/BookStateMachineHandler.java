package com.epam.university.java.project.service;
/*
 * Created by Laptev Egor 02.11.2020
 * */

import com.epam.university.java.project.core.state.machine.domain.StateMachineEventHandler;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookStatus;

public class BookStateMachineHandler implements StateMachineEventHandler {
    public Book onAccept(Book book) {
        book.setState(BookStatus.ACCOUNTED);
        return book;
    }

    public Book onIssue(Book book) {
        book.setState(BookStatus.ISSUED);
        return book;
    }

    public Book onReturn(Book book) {
        book.setState(BookStatus.ACCOUNTED);
        return book;
    }
}
