package com.epam.university.java.project.core.state.machine.domain;
/*
 * Created by Laptev Egor 02.11.2020
 * */

import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookStatus;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "transition")
public class StateMachineStateImpl implements StateMachineState<BookStatus, BookEvent> {
    private BookStatus from;
    private BookStatus to;
    private BookEvent on;
    private String call;

    @Override
    public BookStatus getFrom() {
        return from;
    }

    @Override
    @XmlAttribute(name = "from")
    public void setFrom(BookStatus state) {
        this.from = state;
    }

    @Override
    public BookStatus getTo() {
        return to;
    }

    @Override
    @XmlAttribute(name = "to")
    public void setTo(BookStatus state) {
        this.to = state;
    }

    @Override
    public BookEvent getOn() {
        return on;
    }

    @Override
    @XmlAttribute(name = "on")
    public void setOn(BookEvent event) {
        this.on = event;
    }

    @Override
    public String getMethodToCall() {
        return call;
    }

    @Override
    @XmlAttribute(name = "call")
    public void setMethodToCall(String method) {
        this.call = method;
    }
}
