package org.openblend.guava101.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class OpenCollectionsTestCase {
    @Test
    public void testBasic() throws Exception {
        Internal internal = new Internal();
        External.doSomething(internal.getList());
    }

    private static class Internal {
        private final List<String> list;

        private Internal() {
            list = new ArrayList<String>();
            list.add("FAMNIT");
        }

        /**
         * Do not modify list -- it's immutable!!
         *
         * @return the list
         */
        public List<String> getList() {
            return Collections.unmodifiableList(list);
        }
    }

    private static class External {
        static void doSomething(List<String> list) {
            // list.remove(0);
            // list.add("FMF");
            System.out.println("#1 = " + list.iterator().next());
        }
    }
}
