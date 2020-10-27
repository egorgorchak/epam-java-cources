package com.epam.university.java.project.core.cdi.structure;
/*
 * Created by Laptev Egor 26.10.2020
 * */

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "value")
public class ListItemDefinitionImpl implements ListDefinition.ListItemDefinition {
    private String value;

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    @XmlValue
    public void setValue(String value) {
        this.value = value;
    }
}
