package com.epam.university.java.project.core.cdi.context;
/*
 * Completed by Laptev Egor 20.10.2020
 * */

import com.epam.university.java.project.core.cdi.bean.BeanDefinition;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionImpl;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionRegistryImpl;
import com.epam.university.java.project.core.cdi.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Collection;

public class ApplicationContextImpl implements ApplicationContext {
    @Override
    public int loadBeanDefinitions(Resource resource) {
        File file = resource.getFile();
        Collection<BeanDefinition> beanDefinitions = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BeanDefinitionRegistryImpl.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            BeanDefinitionRegistryImpl beanRegistry = (BeanDefinitionRegistryImpl) unmarshaller.unmarshal(file);
            beanDefinitions = beanRegistry.getBeanDefinitions();
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("Something went wrong in unmarshalling xml");
        }

        System.out.println(beanDefinitions.toString());

        return 0;
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        return 0;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return null;
    }
}
