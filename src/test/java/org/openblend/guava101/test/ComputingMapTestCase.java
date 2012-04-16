package org.openblend.guava101.test;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;
import junit.framework.Assert;
import org.junit.Test;
import org.openblend.guava101.support.ComplexValue;
import org.openblend.guava101.support.Constants;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
@SuppressWarnings("deprecation")
public class ComputingMapTestCase {
    private static final Function<String, ComplexValue> FN = new Function<String, ComplexValue>() {
        public ComplexValue apply(String input) {
            return ComplexValue.hereBeDragons(input);
        }
    };

    @Test
    public void testBefore() throws Exception {
        Map<String, ComplexValue> map = new HashMap<String, ComplexValue>();
        ComplexValue cv = get(map, Constants.VRHNIKA);
        doAssert("akinhrV", cv);
    }

    protected synchronized ComplexValue get(Map<String, ComplexValue> map, String key) {
        ComplexValue cv = map.get(key);
        if (cv == null) {
            cv = ComplexValue.hereBeDragons(key);
            map.put(key, cv);
        }
        return cv;
    }

    @Test
    public void testAfter() throws Exception {
        Map<String, ComplexValue> map = new MapMaker().makeComputingMap(FN);
        ComplexValue cv = map.get(Constants.VRHNIKA);
        doAssert("akinhrV", cv);
    }

    protected void doAssert(String expected, ComplexValue cv) {
        Assert.assertEquals(expected, cv.getString());
    }
}
