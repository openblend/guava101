package org.openblend.guava101.support;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class Constants {
    // persons
    public static final Person ALES = new Person("Ales", "Justin", Gender.MALE);
    public static final Person ANDY = new Person("Andrej", "Brodnik", Gender.MALE);
    public static final Person ELA = new Person("Ela", "Justin", Gender.FEMALE);
    // students
    public static final Student JANEZ = new Student("Janez", "Novak", Gender.MALE);
    // all
    public static final Person[] ALL = {ALES, ANDY, ELA, JANEZ};
    // locations
    public static final String VRHNIKA = "Vrhnika";
    public static final String LJUBLJANA = "Ljubljana";
}
