package com.codellows.tirekicker.controllers;

import com.codellows.tirekicker.models.DinoUser;
import com.codellows.tirekicker.repositories.DinoUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DinoUserController {

  @Autowired
  DinoUserRepository dinoUserRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  HttpServletRequest request;

  @GetMapping("/login")
  public String getLoginPage() { return "login.html"; }

  @GetMapping("/signup")
  public String getSignupPage() { return "signup.html"; }

  @PostMapping("/signup")
  public RedirectView postSignup(String username, String password, String nickname) {
    DinoUser dinoUser = new DinoUser();
    dinoUser.setUsername(username);
    dinoUser.setNickname(nickname);
    String encryptedPassword = passwordEncoder.encode(password);
    dinoUser.setPassword(encryptedPassword);
    dinoUserRepository.save(dinoUser);
    authWithHttpServletRequest(username, password);
    return new RedirectView("/login");
  }

  public void authWithHttpServletRequest(String username, String password) {
    try {
      request.login(username, password);
    } catch (ServletException e) {
      System.out.println("Error while logging in.");
      e.printStackTrace();
    }
  }

  @GetMapping("/")
  public String getIndexPage(Model m, Principal p) {

    System.out.println("Principal " + p);

    if (p != null) {
      String username = p.getName();
      DinoUser dinoUser = dinoUserRepository.findByUsername(username);

      m.addAttribute("username", username);
      m.addAttribute("nickname", dinoUser.getNickname());
    }
    return "index.html";
  }

}
