package com.piffner.demo;

import org.junit.Test;

import static org.junit.Assert.fail;

public class RegularTests {

    // we can do regular tests as well as spring tests
    // just use the normal @Test syntax.
    // This is useful for things like getter/setter tests
    // because they'll be much faster.
    @Test
    public void doATest() {
        fail();
    }
}
