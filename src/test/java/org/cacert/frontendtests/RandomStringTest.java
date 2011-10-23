package org.cacert.frontendtests;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for {RandomString}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class RandomStringTest {

    @Test(expected=IllegalArgumentException.class) public void throwExceptionOnToLessLength() {
        RandomString rs = new RandomString(0);
    }

    @Test public void returnsStringsWithPropperLength() {
        RandomString rs;

        rs = new RandomString(10);
        assertEquals(10, rs.next().length());
        assertEquals(10, rs.next().length());
        assertEquals(10, rs.next().length());
        assertEquals(10, rs.next().length());

        rs = new RandomString(5);
        assertEquals(5, rs.next().length());
        assertEquals(5, rs.next().length());
        assertEquals(5, rs.next().length());
        assertEquals(5, rs.next().length());

        rs = new RandomString(23);
        assertEquals(23, rs.next().length());
        assertEquals(23, rs.next().length());
        assertEquals(23, rs.next().length());
        assertEquals(23, rs.next().length());
    }

    @Test public void doNotGenerateDuplicateStrings() {
        List<String> strings = new ArrayList<String>();
        RandomString rs = new RandomString(10);
        String last;

        for (int i = 0; i < 100; i++) {
            last = rs.next();
            assertFalse(strings.contains(last));
            strings.add(last);
        }
    }
}
