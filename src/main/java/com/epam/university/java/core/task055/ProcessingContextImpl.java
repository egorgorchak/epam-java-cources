package com.epam.university.java.core.task055;
/*
 * Created by Laptev Egor 16.11.2020
 * */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProcessingContextImpl implements ProcessingContext {
    private List<HouseDefinitionImpl> houseDefinitions;

    /**
     * ProcessingContextImpl constructor.
     *
     * @param path to xml file
     */
    public ProcessingContextImpl(String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Dataset.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File sourceFile = new File(
                    getClass().getResource(path).getFile()
            );
            Dataset dataset = (Dataset) unmarshaller.unmarshal(sourceFile);
            houseDefinitions = dataset.getHouseDefinitions();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<HouseDefinition> oldestHouses() {
        ArrayList<HouseDefinition> result = new ArrayList<>();
        int oldest = houseDefinitions.get(0).getYear();
        for (HouseDefinitionImpl houseDefinition : houseDefinitions) {
            int year = houseDefinition.getYear();
            if (year < 1) {
                continue;
            }
            if (year < oldest) {
                oldest = year;
            }
        }
        for (HouseDefinitionImpl houseDefinition : houseDefinitions) {
            int year = houseDefinition.getYear();
            if (year == oldest) {
                result.add(houseDefinition);
            }
        }
        return result;
    }

    @Override
    public int averageAge(String district) {
        List<HouseDefinition> definitions = new ArrayList<>();
        for (HouseDefinitionImpl houseDefinition : houseDefinitions) {
            String currentDistrict = houseDefinition.getDistrict();
            if (houseDefinition.getYear() < 1) {
                continue;
            }
            if (currentDistrict.equals(district)) {
                definitions.add(houseDefinition);
            }
            if (district.equals("Город")) {
                definitions.add(houseDefinition);
            }
        }
        int yearsSum = 0;
        for (HouseDefinition definition : definitions) {
            yearsSum += definition.getYear();
        }
        int average = yearsSum / definitions.size();
        return LocalDate.now().getYear() - 1 - average;
    }

    @Override
    public HouseDefinition biggestTotalFloorSpace() {
        HouseDefinition result = houseDefinitions.get(0);
        for (HouseDefinitionImpl houseDefinition : houseDefinitions) {
            double currentArea = houseDefinition.getArea();
            if (currentArea > result.getArea()) {
                result = houseDefinition;
            }
        }
        return result;
    }

    @Override
    public HouseDefinition smallestTotalFloorSpace() {
        HouseDefinition result = houseDefinitions.get(0);
        for (HouseDefinitionImpl houseDefinition : houseDefinitions) {
            double currentArea = houseDefinition.getArea();
            if (currentArea == 0) {
                continue;
            }
            if (currentArea < result.getArea()) {
                result = houseDefinition;
            }
        }
        return result;
    }

    @Override
    public int totalCountHouses() {
        return houseDefinitions.size();
    }

    @Override
    public int totalCountHousesWithCommunalFlats() {
        int result = 0;
        for (HouseDefinitionImpl houseDefinition : houseDefinitions) {
            String communal = houseDefinition.getCommunal();
            if (!communal.isEmpty()) {
                result++;
            }
        }
        return result;
    }
}
