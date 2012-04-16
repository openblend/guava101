package org.openblend.guava101.support;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class Account {
    private Person person;
    private double state;

    public Account(Person person) {
        this.person = person;
    }

    public Account add(double amount) {
        state += amount;
        return this;
    }

    public Account deduct(double amount) {
        state -= amount;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public double getState() {
        return state;
    }

    @Override
    public int hashCode() {
        return person.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (getClass().equals(obj.getClass()) == false)
            return false;

        Account other = (Account) obj;
        return person.equals(other.person);
    }

    @Override
    public String toString() {
        return person + " (" + state + ")";
    }
}
