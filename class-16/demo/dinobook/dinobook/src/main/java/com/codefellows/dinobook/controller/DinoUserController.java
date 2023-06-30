package com.codefellows.dinobook.controller;

import com.codefellows.dinobook.model.DinoUser;
import com.codefellows.dinobook.repositories.DinoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

// Step 2: Make a controller
@Controller
public class DinoUserController
{
    @Autowired
    DinoUserRepository dinoUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    // Step 7: Make a login page (which Spring Security will POST to magically!)
    @GetMapping("/login")
    public String getLoginPage()
    {
        return "login.html";
    }

    // Step 6A: Make a signup user page and controller method
    @GetMapping("/signup")
    public String getSignupPage()
    {
        return "signup.html";
    }

    // Step 6B: Create user when submitting
    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String nickname)
    {
        DinoUser dinoUser = new DinoUser();
        dinoUser.setUsername(username);
        dinoUser.setNickname(nickname);
        String encryptedPassword = passwordEncoder.encode(password);
        dinoUser.setPassword(encryptedPassword);

        dinoUserRepository.save(dinoUser);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }

    public void authWithHttpServletRequest(String username, String password)
    {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            System.out.println("Error while logging in.");
            e.printStackTrace();
        }
    }
}
