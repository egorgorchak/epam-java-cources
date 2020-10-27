package com.epam.university.java.project.core.cdi.context;
/*
 * Created by Laptev Egor 20.10.2020
 * */

import com.epam.university.java.project.core.cdi.bean.BeanDefinition;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionRegistryImpl;
import com.epam.university.java.project.core.cdi.bean.BeanPropertyDefinition;
import com.epam.university.java.project.core.cdi.io.Resource;
import com.epam.university.java.project.core.cdi.structure.ListDefinition;
import com.epam.university.java.project.core.cdi.structure.MapDefinition;
import com.epam.university.java.project.core.cdi.structure.StructureDefinition;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ApplicationContextImpl implements ApplicationContext {
    private Map<String, Object> singletonBeans = new ConcurrentHashMap<>();
    private Map<BeanDefinition, Class<?>> classBeanDefinitionMap = new ConcurrentHashMap<>();
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
                        beanDefinition, Class.forName(beanDefinition.getClassName())
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
        List<BeanDefinition> searchForBeanDefinition = classBeanDefinitionMap
                .entrySet()
                .stream()
                .filter(entry -> beanClass.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        BeanDefinition beanDefinition = null;

        if (searchForBeanDefinition.size() == 1) {
            beanDefinition = searchForBeanDefinition.get(0);
        } else if (searchForBeanDefinition.size() > 1) {
            for (Map.Entry<BeanDefinition, Class<?>> pair : classBeanDefinitionMap.entrySet()) {
                BeanDefinition definitionToFind = pair.getKey();
                String key = definitionToFind.getId();
                Class<?> value = pair.getValue();
                if (key.equals(beanName) && beanClass.equals(value)) {
                    beanDefinition = definitionToFind;
                }
            }
        }

        if (singletonBeans.containsKey(beanName)) {
            return (T) singletonBeans.get(beanName);
        }

        Object resultObj = null;
        try {
            Constructor<?> constructor = beanClass.getConstructor();
            resultObj = constructor.newInstance();
            Field[] fields = beanClass.getDeclaredFields();

            Collection<BeanPropertyDefinition> properties = beanDefinition.getProperties();
            if (properties == null && beanDefinition.getScope() == null) {
                return (T) resultObj;
            }

            for (Field field : fields) {
                for (BeanPropertyDefinition property : properties) {
                    if (field.getName().equals(property.getName())) {
                        String stringValue = property.getValue();
                        String stringRef = property.getRef();
                        field.setAccessible(true);
                        StructureDefinition data = property.getData();
                        if (stringValue == null && stringRef == null && data == null) {
                            throw new RuntimeException("Value or ref in properties are empty");
                        }
                        if (stringValue != null) {
                            try {
                                int intValue = Integer.parseInt(stringValue);
                                field.set(resultObj, intValue);
                            } catch (NumberFormatException e) {
                                field.set(resultObj, stringValue);
                            }
                        } else if (stringRef != null) {
                            String refName = property.getRef();
                            Object refObject = getBean(refName);
                            field.set(resultObj, refObject);
                        } else if (data != null) {
                            if (data instanceof ListDefinition) {
                                List<String> strings = new ArrayList<>();
                                ListDefinition listDefinition = (ListDefinition) data;
                                Collection<ListDefinition.ListItemDefinition> items =
                                        listDefinition.getItems();
                                for (ListDefinition.ListItemDefinition item : items) {
                                    strings.add(item.getValue());
                                }
                                field.set(resultObj, strings);
                            } else if (data instanceof MapDefinition) {
                                HashMap<String, String> stringStringHashMap = new HashMap<>();
                                HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                                MapDefinition mapDefinition = (MapDefinition) data;
                                Collection<MapDefinition.MapEntryDefinition> values =
                                        mapDefinition.getValues();
                                String key;
                                String val;
                                String ref = null;
                                for (MapDefinition.MapEntryDefinition value : values) {
                                    key = value.getKey();
                                    val = value.getValue();
                                    ref = value.getRef();

                                    if (val != null && ref != null) {
                                        throw new RuntimeException(
                                                "Map with incorrect properties!"
                                        );
                                    }

                                    if (ref == null) {
                                        stringStringHashMap.put(key, val);
                                    } else if (ref != null) {
                                        Object bean = getBean(ref);
                                        stringObjectHashMap.put(key, bean);
                                    }
                                }
                                if (ref == null) {
                                    field.set(resultObj, stringStringHashMap);
                                } else {
                                    field.set(resultObj, stringObjectHashMap);
                                }
                            }
                        }
                    }
                }
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

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
