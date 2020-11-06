package com.epam.university.java.project.core.cdi.bean;
/*
 * Created by Laptev Egor 21.10.2020
 * */

import com.epam.university.java.project.core.cdi.structure.ListDefinitionImpl;
import com.epam.university.java.project.core.cdi.structure.MapDefinitionImpl;
import com.epam.university.java.project.core.cdi.structure.StructureDefinition;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "property")
@XmlType(propOrder = {"name", "value", "ref", "data"})
public class BeanPropertyDefinitionImpl implements BeanPropertyDefinition {
    private String name;
    private String value;
    private String ref;
    private StructureDefinition data;

    @Override
    public String getName() {
        return name;
    }

    @Override
    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    @XmlAttribute(name = "value")
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getRef() {
        return ref;
    }

    @Override
    @XmlAttribute(name = "ref")
    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public StructureDefinition getData() {
        return data;
    }

    @Override
    @XmlElements({
            @XmlElement(name = "list", type = ListDefinitionImpl.class),
            @XmlElement(name = "map", type = MapDefinitionImpl.class)
    })
    public void setData(StructureDefinition data) {
        this.data = data;
    }
}
