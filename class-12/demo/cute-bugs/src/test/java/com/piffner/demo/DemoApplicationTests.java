package com.piffner.demo;

import com.piffner.demo.controllers.BugController;
import com.piffner.demo.controllers.HelloController;
import com.piffner.demo.database.Bug;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    /**
     * This will create the HelloController
     * _and_ the HellosService automagically
     */
    @Autowired
    HelloController controller;

    /**
     * This will create the bug controller with the repo available
     * Autowired is necessary to get a repository object in this case
     */
    @Autowired
    BugController bugController;

    @Test
    public void testController() {
        // once autowired, you can test the controller like normal
        String results = this.controller.postHello("Bob", 5);
        assertEquals("Hi Bob5", results);
    }

    @Test
    public void testBugController() {
        // This is our first integration test!
        // getBugs() will make a database query
        // so your db has to be configured and working to for this test to run
        List<Bug> bugs = (List<Bug>) bugController.getBugs();
        assertEquals(1, bugs.size());
    }

    @Test
    public void contextLoads() {
    }

}
