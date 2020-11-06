package com.epam.university.java.project.service;
/*
 * Created by Laptev Egor 30.10.2020
 * */

import com.epam.university.java.project.core.cdi.io.Resource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinitionImpl;
import com.epam.university.java.project.core.state.machine.domain.StateMachineEventHandler;
import com.epam.university.java.project.core.state.machine.domain.StateMachineState;
import com.epam.university.java.project.core.state.machine.domain.StatefulEntity;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookStatus;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.lang.reflect.Method;
import java.util.Collection;

public class StateMachineManagerImpl implements StateMachineManager {
    @Override
    public StateMachineDefinition<BookStatus, BookEvent> loadDefinition(Resource resource) {
        StateMachineDefinitionImpl loadedDefinition =
                new StateMachineDefinitionImpl();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(StateMachineDefinitionImpl.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            loadedDefinition =
                    (StateMachineDefinitionImpl) unmarshaller.unmarshal(resource.getFile());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return loadedDefinition;
    }

    @Override
    public <S, E> StatefulEntity<S, E> initStateMachine(
            StatefulEntity<S, E> entity, StateMachineDefinition<S, E> definition
    ) {
        entity.setState(definition.getStartState());
        entity.setStateMachineDefinition(definition);
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S, E> StatefulEntity<S, E> handleEvent(StatefulEntity<S, E> entity, E event) {
        // entity -> SM definition -> handler class -> handler instance
        // SM definition -> states collection
        //                  -> iter through collection -> state.getOn() == EVENT -> method to invoke

        StateMachineDefinition<S, E> definition = entity.getStateMachineDefinition();
        Class<? extends StateMachineEventHandler> handlerClass = definition.getHandlerClass();
        Collection<StateMachineState<S, E>> definitionStates = definition.getStates();

        for (StateMachineState<S, E> state : definitionStates) {
            if (state.getOn().equals(event) && state.getFrom().equals(entity.getState())) {
                String methodToCall = state.getMethodToCall();
                try {
                    StateMachineEventHandler handlerInstance = handlerClass
                            .getConstructor()
                            .newInstance();
                    Method method = handlerClass.getMethod(methodToCall, Book.class);
                    entity = (StatefulEntity<S, E>) method.invoke(handlerInstance, entity);
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            }
        }
        return entity;
    }
}
