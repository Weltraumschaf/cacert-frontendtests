package org.cacert.frontendtests;

import java.util.Random;

/**
 * Utility class which generates random string with a specific length.
 *
 * From http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string-in-java
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class RandomString {

    /**
     * Holds the used characters.
     */
    private static final char[] symbols = new char[36];

    // Intitialize used characters: 0..9 | a..z
    static {
        for (int idx = 0; idx < 10; ++idx) {
            symbols[idx] = (char) ('0' + idx);
        }
        for (int idx = 10; idx < 36; ++idx) {
            symbols[idx] = (char) ('a' + idx - 10);
        }
    }

    /**
     * Internal pseud random generator. Not secure!
     */
    private final Random random = new Random();
    /**
     * Buffers the generated String.
     */
    private final char[] buf;

    /**
     * Initializes generator with a length;
     *
     * @param length
     */
    public RandomString(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length < 1: " + length);
        }

        buf = new char[length];
    }

    /**
     * Gives a new generated random String.
     *
     * @return
     */
    public String next() {
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }
}
