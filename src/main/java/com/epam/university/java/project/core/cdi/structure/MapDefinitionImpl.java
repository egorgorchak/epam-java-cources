package com.epam.university.java.project.core.cdi.structure;
/*
 * Created by Laptev Egor 26.10.2020
 * */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "map")
public class MapDefinitionImpl implements MapDefinition {
    private Collection<MapEntryDefinition> values;

    @Override
    public Collection<MapEntryDefinition> getValues() {
        return this.values;
    }

    @Override
    @XmlElement(name = "entry", type = MapEntryDefinitionImpl.class)
    public void setValues(Collection<MapEntryDefinition> values) {
        this.values = values;
    }
}
