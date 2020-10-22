package com.epam.university.java.project.core.cdi.bean;
/*
 * Completed by Laptev Egor 21.10.2020
 * */

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Collection;

@XmlRootElement(name = "bean")
@XmlType(propOrder = {"id", "className", "postConstruct", "scope", "properties"})
public class BeanDefinitionImpl implements BeanDefinition {
    private String id;
    private String className;
    private String postConstruct;
    private String scope;
    private Collection<BeanPropertyDefinition> properties;


    @Override
    public String getId() {
        return id;
    }

    @Override
    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    @XmlAttribute(name = "class")
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String getPostConstruct() {
        return postConstruct;
    }

    @Override
    @XmlAttribute(name = "init")
    public void setPostConstruct(String postConstruct) {
        this.postConstruct = postConstruct;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    @XmlAttribute(name = "scope")
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public Collection<BeanPropertyDefinition> getProperties() {
        return properties;
    }

    @Override
    @XmlElement(name = "property", type = BeanPropertyDefinitionImpl.class)
    public void setProperties(Collection<BeanPropertyDefinition> properties) {
        this.properties = properties;
    }
}
