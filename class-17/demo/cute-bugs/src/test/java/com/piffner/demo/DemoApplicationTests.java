package com.piffner.demo;

import com.piffner.demo.controllers.BugController;
import com.piffner.demo.controllers.HelloController;
import com.piffner.demo.database.Bug;
import com.piffner.demo.database.BugRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webapplicationContext;

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

    @Autowired
    BugRepository repo;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webapplicationContext).build();
    }

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
    public void testGetsBugs() throws Exception {
        //createFiveBugs();
        //createBugs(5);

        Bug bug = new Bug();
        bug.name = "George";
        repo.save(bug);

        Bug bug1 = new Bug();
        bug1.name = "Fred";
        repo.save(bug1);

        // Note all the setup above required to setup the mockMvc
        // we can easily check the status, the content type, and the view
        // to check the 'content' (the returned hmtl text) we have to use the Matchers.*
        mockMvc.perform(MockMvcRequestBuilders.get("/bugfarm"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("bugs"))
                .andExpect(content().string(Matchers.containsString("George")))
                .andExpect(content().string(Matchers.containsString("Fred")))
                .andExpect(content().string(Matchers.containsString("Bugs")));
    }

    @Test
    public void testSignsUpUser() throws Exception {
        String username = "my username";
        String password = "my password";
        String favQueen = "my fav queen";

        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                .param("username", username)
                .param("password", password)
                .param("favoriteQueen", favQueen))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("login"));
//                .andExpect(content().string(Matchers.containsString("George")))
//                .andExpect(content().string(Matchers.containsString("Fred")))
//                .andExpect(content().string(Matchers.containsString("Bugs")));
    }

    @Test
    public void contextLoads() {
    }

}
