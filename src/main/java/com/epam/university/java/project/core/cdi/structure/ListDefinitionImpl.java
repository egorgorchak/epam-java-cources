package com.epam.university.java.project.core.cdi.structure;
/*
 * Created by Laptev Egor 26.10.2020
 * */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "list")
public class ListDefinitionImpl implements ListDefinition {
    private Collection<ListItemDefinition> listItemDefinitions;

    @Override
    public Collection<ListItemDefinition> getItems() {
        return this.listItemDefinitions;
    }

    @Override
    @XmlElement(name = "value", type = ListItemDefinitionImpl.class)
    public void setItems(Collection<ListItemDefinition> items) {
        this.listItemDefinitions = items;
    }
}
