package com.epam.university.java.project.core.state.machine.domain;
/*
 * Created by Laptev Egor 01.11.2020
 * */

import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookStatus;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "definition")
public class StateMachineDefinitionImpl implements StateMachineDefinition<BookStatus, BookEvent> {
    private BookEvent startEvent;
    private BookStatus startState;
    private Collection<StateMachineState<BookStatus, BookEvent>> states;
    private String handlerClass;

    @Override
    public BookEvent getStartEvent() {
        return startEvent;
    }

    @Override
    @XmlAttribute(name = "startEvent")
    public void setStartEvent(BookEvent event) {
        this.startEvent = event;
    }

    @Override
    public BookStatus getStartState() {
        return startState;
    }

    @Override
    @XmlAttribute(name = "startState")
    public void setStartState(BookStatus state) {
        this.startState = state;
    }

    @Override
    public Collection<StateMachineState<BookStatus, BookEvent>> getStates() {
        return states;
    }

    @XmlElement(name = "transition", type = StateMachineStateImpl.class)
    public void setStates(Collection<StateMachineState<BookStatus, BookEvent>> states) {
        this.states = states;
    }

    @Override
    public void addState(StateMachineState<BookStatus, BookEvent> state) {
        states.add(state);
    }

    @Override
    public Class<? extends StateMachineEventHandler> getHandlerClass() {
        Class<? extends StateMachineEventHandler> aClass = null;
        try {
            aClass = (Class<? extends StateMachineEventHandler>) Class.forName(handlerClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return aClass;
    }

    @Override
    @XmlAttribute(name = "handler")
    public void setHandlerClass(Class<? extends StateMachineEventHandler> handlerClass) {
        this.handlerClass = handlerClass.getName();
    }
}
