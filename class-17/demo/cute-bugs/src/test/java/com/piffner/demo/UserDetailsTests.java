package com.piffner.demo;

import com.piffner.demo.database.AppUser;
import com.piffner.demo.database.AppUserRepository;
import com.piffner.demo.security.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsTests {

    // wire in the user details to test
    // will auto inject the repo
    @Autowired
    UserDetailsServiceImpl service;

    @Autowired
    AppUserRepository repo;

    @Test
    public void testUserDetails() {
        AppUser details = new AppUser();
        details.setUsername("test");

        AppUser details2 = new AppUser();
        details2.setUsername("test");

        // save to repo so we can test it
        repo.save(details);
//        repo.save(details2);  // will throw due to duplicate


        // should load!
        UserDetails results = service.loadUserByUsername("test");
        assertEquals("test", results.getUsername());

        // should be null
        UserDetails results2 = service.loadUserByUsername("I DON'T EXIST");
        assertNull(results2);
    }
}
