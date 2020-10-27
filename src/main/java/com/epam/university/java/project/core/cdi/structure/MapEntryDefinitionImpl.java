package com.epam.university.java.project.core.cdi.structure;
/*
 * Created by Laptev Egor 27.10.2020
 * */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
public class MapEntryDefinitionImpl implements MapDefinition.MapEntryDefinition {
    private String key;
    private String value;
    private String ref;

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    @XmlElement(name = "key")
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    @XmlElement(name = "value")
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getRef() {
        return this.ref;
    }

    @Override
    @XmlElement(name = "ref")
    public void setRef(String reference) {
        this.ref = reference;
    }
}
