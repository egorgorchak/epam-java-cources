package com.epam.university.java.core.task041;
/*
 * Completed by Laptev Egor 07.10.2020
 * */

import java.util.Collection;

public class EntityImpl implements Entity {
    private String value;
    private int id;

    public EntityImpl(String value, int id) {
        this.value = value;
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Entity entity = (Entity) obj;
        return this.value.equals(entity.getValue()) && this.id == entity.getId();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.id;
        return result;
    }
}
