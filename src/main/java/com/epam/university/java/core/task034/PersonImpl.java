package com.epam.university.java.core.task034;
/*
 * Completed by Laptev Egor 10.10.2020
 * */

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Collection;

@XmlRootElement(name = "person")
@XmlType(propOrder = {"firstName", "lastName", "phoneNumbers"})
public class PersonImpl implements Person {
    private int id;
    private String firstName;
    private String lastName;
    private Collection<PhoneNumber> phoneNumbers;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    @XmlElement(name = "first-name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    @XmlElement(name = "last-name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Collection<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    @Override
    @XmlElementWrapper(name = "person-phones")
    @XmlElement(name = "person-phone", type = PhoneNumberImpl.class)
    public void setPhoneNumbers(Collection<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
