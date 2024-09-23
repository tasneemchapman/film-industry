package edu.psu.ist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class MovieTests {

    @Test public void smokeTest01() {
        // should pass...
        Assertions.assertEquals("I'm a string!", "I'm a string!");
    }
}
