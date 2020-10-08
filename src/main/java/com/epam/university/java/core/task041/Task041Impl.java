package com.epam.university.java.core.task041;
/*
 * Completed by Laptev Egor 07.10.2020
 * */

import java.util.Collection;

public class Task041Impl implements Task041 {
    @Override
    public Entity create(Collection<Entity> collection, String value) {
        if (collection == null || value == null) {
            throw new IllegalArgumentException();
        }
        Entity entity = new EntityImpl(value, 0);
        collection.add(entity);
        return entity;
    }

    @Override
    public Entity read(Collection<Entity> collection, Entity entity) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }
        return entity;
    }

    @Override
    public void update(Collection<Entity> collection, Entity entity, String value) {
        if (collection == null || entity == null || value == null || !collection.contains(entity)) {
            throw new IllegalArgumentException();
        }
        ((EntityImpl) entity).setValue(value);
    }

    @Override
    public void delete(Collection<Entity> collection, Entity entity) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }
        collection.remove(entity);
    }
}
