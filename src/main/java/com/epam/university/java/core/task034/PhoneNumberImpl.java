package com.epam.university.java.core.task034;
/*
 * Completed by Laptev Egor 10.10.2020
 * */

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "person-phone")
public class PhoneNumberImpl implements PhoneNumber {
    private String phoneNumber;

    public PhoneNumberImpl() {
    }

    public PhoneNumberImpl(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    @XmlValue
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
