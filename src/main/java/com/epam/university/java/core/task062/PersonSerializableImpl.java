package com.epam.university.java.core.task062;
/*
 * Created by Laptev Egor 15.11.2020
 * */

import java.util.Collection;
import java.util.Objects;

public class PersonSerializableImpl implements PersonSerializable {
    private String fullName;
    private int age;
    private boolean isMale;
    private PersonSerializable spouse;
    private Collection<PersonSerializable> children;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public void setSpouse(PersonSerializable spouse) {
        this.spouse = spouse;
    }

    public void setChildren(Collection<PersonSerializable> children) {
        this.children = children;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = result + Objects.hashCode(fullName);
        result = result + age;
        result = result + Objects.hashCode(isMale);
        result = result + Objects.hashCode(spouse);
        result = result + Objects.hashCode(children);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        PersonSerializableImpl person = (PersonSerializableImpl) obj;
        if (person.fullName != null
                ? !person.fullName.equals(this.fullName) : this.fullName != null) {
            return false;
        }
        if (person.age != this.age) {
            return false;
        }
        if (person.isMale != this.isMale) {
            return false;
        }
        if (person.spouse != null ? !person.spouse.equals(this.spouse) : this.spouse != null) {
            return false;
        }
        if (person.children != null
                ? !person.children.containsAll(this.children) : this.children != null) {
            return false;
        }
        return true;
    }
}
