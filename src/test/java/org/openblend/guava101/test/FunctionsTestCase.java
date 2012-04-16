package org.openblend.guava101.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.openblend.guava101.support.Account;
import org.openblend.guava101.support.Constants;
import org.openblend.guava101.support.Person;
import org.openblend.guava101.support.Student;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
@SuppressWarnings("deprecation")
public class FunctionsTestCase {
    private List<Person> ALL = Arrays.asList(Constants.ALL);
    private static final long LIMIT = 1000000L;  // 1M$

    private static final Function<Person, String> FN_PERSON_LABEL = new Function<Person, String>() {
        public String apply(Person input) {
            return input.toString();
        }
    };

    private static final Function<Person, Account> FN_INITIAL_ACCOUNT = new Function<Person, Account>() {
        public Account apply(Person input) {
            return new Account(input).add(4500);
        }
    };

    private static final Function<Account, Account> FN_AFTER_TAX = new Function<Account, Account>() {
        public Account apply(Account input) {
            return input.deduct(input.getState() * .20);
        }
    };

    private static final Function<Account, Account> FN_AFTER_TAX_COPY = new Function<Account, Account>() {
        public Account apply(Account input) {
            return new Account(input.getPerson()).add(input.getState() * .80);
        }
    };

    private static final Function<Account, Account> FN_AFTER_COST = new Function<Account, Account>() {
        public Account apply(Account input) {
            return input.deduct(100);
        }
    };

    private static final Predicate<Account> PR_RICH = new Predicate<Account>() {
        public boolean apply(Account input) {
            return input.getState() > LIMIT;
        }
    };

    private static final Predicate<Person> PR_STUDENT = new Predicate<Person>() {
        public boolean apply(Person input) {
            return (input instanceof Student);
        }
    };

    @Test
    public void testBefore() throws Exception {
        Person janez = null;
        for (Person p : ALL) {
            if (p instanceof Student) {
                janez = p;
                break;
            }
        }
        doPrint("Student", janez);

        Map<String, Person> map = new HashMap<String, Person>();
        for (Person p : ALL) {
            map.put(p.toString(), p);
        }
        doPrint("Map", map);

        Map<String, Account> bank = new LinkedHashMap<String, Account>();
        for (Map.Entry<String, Person> entry : map.entrySet()) {
            bank.put(entry.getKey(), new Account(entry.getValue()).add(4500));
        }
        doPrint("Bank", bank);

        // make Ela rich
        bank.get(FN_PERSON_LABEL.apply(Constants.ELA)).add(2 * LIMIT);

        Map<String, Account> rich = new HashMap<String, Account>();
        for (Map.Entry<String, Account> entry : bank.entrySet()) {
            if (entry.getValue().getState() > LIMIT) {
                rich.put(entry.getKey(), entry.getValue());
            }
        }
        doPrint("Rich", rich);

        // make Andy rich, but not too rich :-)
        bank.get(FN_PERSON_LABEL.apply(Constants.ANDY)).add(LIMIT);

        Person ela = null;
        for (Map.Entry<String, Account> entry : bank.entrySet()) {
            if (entry.getValue().getState() * .80 > LIMIT) {
                ela = entry.getValue().getPerson();
                break;
            }
        }
        doPrint("After taxes", ela);

        List<Account> taxed = new ArrayList<Account>();
        for (Account a : bank.values()) {
            taxed.add(a.deduct(a.getState() * .20).deduct(100));
        }
        doPrint("Taxed", taxed);
    }

    @Test
    public void testAfter() throws Exception {
        doPrint("Student", Iterables.find(ALL, PR_STUDENT));

        Map<String, Person> map = Maps.uniqueIndex(ALL, FN_PERSON_LABEL);
        doPrint("Map", map);

        Map<String, Account> bank = new LinkedHashMap<String, Account>(Maps.transformValues(map, FN_INITIAL_ACCOUNT));
        doPrint("Bank", bank);

        // make Ela rich
        bank.get(FN_PERSON_LABEL.apply(Constants.ELA)).add(2 * LIMIT);

        Map<String, Account> rich = Maps.filterValues(bank, PR_RICH);
        doPrint("Rich", rich);

        // make Andy rich, but not too rich :-)
        bank.get(FN_PERSON_LABEL.apply(Constants.ANDY)).add(LIMIT);

        Person ela = Iterables.find(bank.values(), Predicates.compose(PR_RICH, FN_AFTER_TAX_COPY)).getPerson();
        doPrint("After taxes", ela);

        Iterable<Account> taxed = Iterables.transform(bank.values(), Functions.compose(FN_AFTER_COST, FN_AFTER_TAX));
        doPrint("Taxed", taxed);
    }

    protected void doPrint(String msg, Object obj) {
        System.out.println(msg + ": " + obj);
    }
}
