package com.epam.university.java.core.task055;
/*
 * Created by Laptev Egor 16.11.2020
 * */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passports_houses")
public class HouseDefinitionImpl implements HouseDefinition {
    private String address;
    private int year;
    private double area;
    private String communal;
    private String district;

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    @XmlElement(name = "address")
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    @XmlElement(name = "data_buildingdate")
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    @XmlElement(name = "data_buildingarea")
    public void setArea(double area) {
        this.area = area;
    }

    public String getCommunal() {
        return communal;
    }

    @XmlElement(name = "comm_num")
    public void setCommunal(String communal) {
        this.communal = communal;
    }

    public String getDistrict() {
        return district;
    }

    @XmlElement(name = "addr_district")
    public void setDistrict(String district) {
        this.district = district;
    }


}
