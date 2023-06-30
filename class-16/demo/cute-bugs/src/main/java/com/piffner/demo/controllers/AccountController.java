package com.piffner.demo.controllers;

import com.piffner.demo.database.AppUser;
import com.piffner.demo.database.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    @Autowired
    AppUserRepository repo;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }

    // POST signup
    // save to database
    @PostMapping("/signup")
    public String postSignup(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String favoriteQueen
    ) {
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setFavoriteQueen(favoriteQueen);

        repo.save(user);

        // TODO auto log them in?
        return "/login";
    }

    // login

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/login-error")
    @ResponseBody
    public String getLoginError() {
        return "BAD USERNAME OR PASSWORD";
    }
}
