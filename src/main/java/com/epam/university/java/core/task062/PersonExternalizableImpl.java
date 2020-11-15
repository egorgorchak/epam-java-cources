package com.epam.university.java.core.task062;
/*
 * Created by Laptev Egor 15.11.2020
 * */

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Objects;

public class PersonExternalizableImpl implements PersonExternalizable, Externalizable {
    private String fullName;
    private int age;
    private boolean isMale;
    private PersonExternalizable spouse;
    private Collection<PersonExternalizable> children;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public void setSpouse(PersonExternalizable spouse) {
        this.spouse = spouse;
    }

    public void setChildren(Collection<PersonExternalizable> children) {
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
        PersonExternalizableImpl person = (PersonExternalizableImpl) obj;
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(fullName);
        out.writeObject(age);
        out.writeObject(isMale);
        out.writeObject(spouse);
        out.writeObject(children);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setFullName((String) in.readObject());
        setAge((Integer) in.readObject());
        setMale((Boolean) in.readObject());
        setSpouse((PersonExternalizableImpl) in.readObject());
        setChildren((Collection<PersonExternalizable>) in.readObject());
    }
}
