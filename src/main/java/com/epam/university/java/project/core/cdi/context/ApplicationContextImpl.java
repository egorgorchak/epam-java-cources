package com.epam.university.java.project.core.cdi.context;
/*
 * Completed by Laptev Egor 20.10.2020
 * */

import com.epam.university.java.project.core.cdi.bean.BeanDefinition;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionRegistryImpl;
import com.epam.university.java.project.core.cdi.bean.BeanPropertyDefinition;
import com.epam.university.java.project.core.cdi.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class ApplicationContextImpl implements ApplicationContext {
    @Override
    public int loadBeanDefinitions(Resource resource) {
        File resourceFile = resource.getFile();
        Collection<BeanDefinition> beanDefinitions = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BeanDefinitionRegistryImpl.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            BeanDefinitionRegistryImpl beanRegistry =
                    (BeanDefinitionRegistryImpl) unmarshaller.unmarshal(resourceFile);
            beanDefinitions = beanRegistry.getBeanDefinitions();
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("Something went wrong in unmarshalling xml");
        }

        int definitionsAmount = 0;
        if (beanDefinitions != null) {
            definitionsAmount = beanDefinitions.size();
            new BeanDefinitionRegistryImpl().setBeanDefinitions(beanDefinitions);
        }

        return definitionsAmount;
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        return 0;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        //get beanDefinition from registry
        BeanDefinition beanDefinition = new BeanDefinitionRegistryImpl().getBeanDefinition(beanClass.getName());
        //create bean

        //return bean
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = new BeanDefinitionRegistryImpl().getBeanDefinition(beanName);
        String className = beanDefinition.getClassName();
        Object resultObj = null;
        try {
            Class<?> aClass = Class.forName(className);
            Constructor<?> constructor = aClass.getConstructor();
            resultObj = constructor.newInstance();

            Field[] fields = Class.forName(className).getDeclaredFields();
            for (Field field : fields) {
                for (BeanPropertyDefinition property : beanDefinition.getProperties()) {
                    if (field.getName().equals(property.getName())) {
                        field.setAccessible(true);
                        String stringValue = property.getValue();
                        int intValue;
                        try {
                            intValue = Integer.parseInt(stringValue);
                            field.set(resultObj, intValue);
                        } catch (NumberFormatException e) {
                            field.set(resultObj, stringValue);
                        }
                    }
                }
            }

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }



        return null;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return null;
    }
}
