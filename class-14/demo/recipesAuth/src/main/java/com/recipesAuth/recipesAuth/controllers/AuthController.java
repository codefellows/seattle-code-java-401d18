package com.recipesAuth.recipesAuth.controllers;

import com.recipesAuth.recipesAuth.models.SiteUser;
import com.recipesAuth.recipesAuth.repos.SiteUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.mindrot.jbcrypt.BCrypt;

@Controller
public class AuthController {

  @Autowired
  SiteUserRepository siteUserRepository;

  @GetMapping("/")
  public String getLogin() {
    return "login.html";
  }

  @PostMapping("/login")
  public RedirectView login(HttpServletRequest request, String userName, String password) {

    // Confirm that password is correct for given user
    // 1. Find the corresponding user
    SiteUser siteUser = siteUserRepository.findSiteUserByUserName(userName);

    if(siteUser == null) {
      return new RedirectView("/");
    }

    // 2. Check if stored password matches
    if(!BCrypt.checkpw(password, siteUser.getPassword())) {
      return new RedirectView("/");
    }

    // store in session
    HttpSession session = request.getSession();
    session.setAttribute("userName", userName);

    return new RedirectView("/recipes");
  }

  @PostMapping("/signup")
  public RedirectView signup(String userName, String password) {

    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

    SiteUser newUser = new SiteUser(userName, hashedPassword);
    siteUserRepository.save(newUser);

    return new RedirectView("/");
  }

  @GetMapping("/logout")
  public RedirectView logout(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.invalidate();
    return new RedirectView("/");
  }



}
