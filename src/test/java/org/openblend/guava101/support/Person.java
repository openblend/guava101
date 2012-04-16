package org.openblend.guava101.support;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class Person {
    private String name;
    private String surname;
    private Gender gender;

    public Person(String name, String surname, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + 3 * surname.hashCode() + 7 * gender.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (getClass().equals(obj.getClass()) == false)
            return false;

        Person other = (Person) obj;
        return name.equals(other.name) && surname.equals(other.surname) && gender == other.gender;
    }

    @Override
    public String toString() {
        return name + " " + surname + " (" + gender.name().charAt(0) + ")";
    }
}
