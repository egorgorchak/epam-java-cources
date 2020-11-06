package com.epam.university.java.project.service;
/*
 * Created by Laptev Egor 30.10.2020
 * */

import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookDaoXmlImpl implements BookDao {
    private List<Book> books = new ArrayList<>();

    @Override
    public Book createBook() {
        return new BookImpl();
    }

    @Override
    public Book getBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Collection<Book> getBooks() {
        return books;
    }

    @Override
    public void remove(Book book) {
        books.remove(book);
    }

    @Override
    public Book save(Book book) {
        books.add(book);
        book.setId(books.size() + 1);
        return book;
    }
}
