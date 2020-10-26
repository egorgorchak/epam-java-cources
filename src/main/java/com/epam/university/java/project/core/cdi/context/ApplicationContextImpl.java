package com.epam.university.java.project.core.cdi.context;
/*
 * Created by Laptev Egor 20.10.2020
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
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContextImpl implements ApplicationContext {
    private Map<String, Object> singletonBeans = new ConcurrentHashMap<>();
    private Map<Class<?>, BeanDefinition> classBeanDefinitionMap = new ConcurrentHashMap<>();
    private BeanDefinitionRegistryImpl definitionRegistry = new BeanDefinitionRegistryImpl();

    @Override
    public int loadBeanDefinitions(Resource resource) {
        File resourceFile = resource.getFile();
        Collection<BeanDefinition> beanDefinitions = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BeanDefinitionRegistryImpl.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            definitionRegistry = (BeanDefinitionRegistryImpl) unmarshaller.unmarshal(resourceFile);
            beanDefinitions = definitionRegistry.getBeanDefinitions();
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("Something went wrong in unmarshalling xml");
        }

        int definitionsAmount;
        if (beanDefinitions == null) {
            throw new RuntimeException("Bean definitions are not set!");
        }

        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                classBeanDefinitionMap.put(
                        Class.forName(beanDefinition.getClassName()), beanDefinition
                );
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        definitionsAmount = beanDefinitions.size();
        definitionRegistry.setBeanDefinitions(beanDefinitions);
        return definitionsAmount;
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        return 0;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        StringBuilder beanName = new StringBuilder(beanClass.getSimpleName());
        String c = String.valueOf(Character.toLowerCase(beanName.charAt(0)));
        beanName.replace(0,1, c);

        if (beanClass.isInterface()) {
            Collection<BeanDefinition> beanDefinitions = definitionRegistry.getBeanDefinitions();
            Class<T> implClass = null;
            for (BeanDefinition beanDef : beanDefinitions) {
                Class<?> aClass = null;
                try {
                    aClass = Class.forName(beanDef.getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Class<?>[] interfaces = aClass.getInterfaces();
                for (Class<?> anInterface : interfaces) {
                    if (anInterface.equals(beanClass)) {
                        implClass = (Class<T>) aClass;
                    }
                }
            }
            if (implClass == null) {
                throw new RuntimeException("Unable to get bean by interface");
            }
            return getBean(beanName.toString(), implClass);
        } else {

            return getBean(beanName.toString(), beanClass);
        }
    }

    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = definitionRegistry.getBeanDefinition(beanName);
        String className = beanDefinition.getClassName();
        Class<?> aClass = null;
        try {
            aClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return getBean(beanName, aClass);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        //load bean definition by name from registry
        BeanDefinition beanDefinition = classBeanDefinitionMap.get(beanClass);

        //check if the map contains specific id
        if (singletonBeans.containsKey(beanName)) {
            return (T) singletonBeans.get(beanName);
        }

        Object resultObj = null;
        try {
            Constructor<?> constructor = beanClass.getConstructor();
            resultObj = constructor.newInstance();
            Field[] fields = beanClass.getDeclaredFields();
            for (Field field : fields) {
                for (BeanPropertyDefinition property : beanDefinition.getProperties()) {
                    if (field.getName().equals(property.getName())) {
                        field.setAccessible(true);
                        String stringValue = property.getValue();
                        String stringRef = property.getRef();
                        if (stringValue == null && stringRef == null) {
                            throw new RuntimeException("Value or ref in properties are empty");
                        }
                        if (stringValue != null) {
                            int intValue;
                            try {
                                intValue = Integer.parseInt(stringValue);
                                field.set(resultObj, intValue);
                            } catch (NumberFormatException e) {
                                field.set(resultObj, stringValue);
                            }
                        } else {
                            String refName = property.getRef();
                            Object refObject = getBean(refName);
                            field.setAccessible(true);
                            field.set(resultObj, refObject);
                        }
                    }
                }
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

        //if created bean is not presented in singletoBean map - put it to the map
        String scope = beanDefinition.getScope();
        if (scope != null && scope.equals("singleton")) {
            singletonBeans.put(beanDefinition.getId(), resultObj);
        }

        if (resultObj == null) {
            throw new RuntimeException("Unable to get object");
        }
        return (T) resultObj;
    }
}
