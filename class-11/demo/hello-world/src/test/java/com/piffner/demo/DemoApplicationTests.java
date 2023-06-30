package com.piffner.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void testHello() {
        // A controller is just a class!
        // w/out spring, the annotations don't do anything
        // and the methods can be called directly to test
        HelloController hello = new HelloController();
        String results = hello.getNamedHello("Bob");
        assertEquals("Hello, Bob", results);

        // if you have a template route with a model
        // you can pass in an ExtendedModelMap
        ExtendedModelMap model = new ExtendedModelMap();
        String templateName = hello.moreComplex("Bob", model);

        // the results will be the template name (the returned string)
        assertEquals("complex", templateName);

        // and you can get the objects out of the model
        Information info = (Information) model.get("info");
        assertEquals("Bob", info.name);
        assertEquals(7, info.number);
    }
}
