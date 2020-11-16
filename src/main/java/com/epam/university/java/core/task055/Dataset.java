package com.epam.university.java.core.task055;
/*
 * Created by Laptev Egor 16.11.2020
 * */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "dataset")
public class Dataset {
    private List<HouseDefinitionImpl> houseDefinitions;

    /**
     * Get house definitions.
     *
     * @return collection of house definitions
     */
    public List<HouseDefinitionImpl> getHouseDefinitions() {
        return houseDefinitions;
    }

    /**
     * Set house definitions.
     *
     * @param houseDefinitions collection of house definitions
     */
    @XmlElement(name = "passports_houses", type = HouseDefinitionImpl.class)
    public void setHouseDefinitions(List<HouseDefinitionImpl> houseDefinitions) {
        this.houseDefinitions = houseDefinitions;
    }
}
