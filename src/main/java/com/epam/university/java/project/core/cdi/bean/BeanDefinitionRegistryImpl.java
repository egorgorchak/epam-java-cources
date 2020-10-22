package com.epam.university.java.project.core.cdi.bean;
/*
 * Completed by Laptev Egor 22.10.2020
 * */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "beans")
public class BeanDefinitionRegistryImpl implements BeanDefinitionRegistry {
    private static Collection<BeanDefinition> beanDefinitions;

    public Collection<BeanDefinition> getBeanDefinitions() {
        return beanDefinitions;
    }

    @XmlElement(name = "bean", type = BeanDefinitionImpl.class)
    public void setBeanDefinitions(Collection<BeanDefinition> beanDefinitions) {
        BeanDefinitionRegistryImpl.beanDefinitions = beanDefinitions;
    }

    @Override
    public void addBeanDefinition(BeanDefinition definition) {
        beanDefinitions.add(definition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return null;
    }
}
