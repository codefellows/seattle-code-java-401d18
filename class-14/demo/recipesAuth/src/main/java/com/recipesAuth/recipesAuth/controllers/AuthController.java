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
  public String getHome() {
    return "login.html";
  }

  // post mapping for sign up
  @PostMapping("/signup")
  public RedirectView signUp(String userName, String password) {
    // hashpw
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
    // create a new user (with the hashed password)
    SiteUser newUser = new SiteUser(userName, hashedPassword);

    //save to DB
    siteUserRepository.save(newUser);

    //redirect?
    return new RedirectView("/");
  }

  // post mapping for login
  @PostMapping("/login")
  public RedirectView login(HttpServletRequest request, String userName, String password, Model m) {
    //find user by username
    SiteUser siteUser = siteUserRepository.getSiteUserByUserName(userName);

    // conditional to check if they're a legit user (if not, send them back to the splash page)
    if(siteUser != null) {
      // if they are, check if the password they gave is legit (if not, send back ot splash page)
      if(BCrypt.checkpw(password, siteUser.getPassword())) {
        //if its a legit password, make a session and log them in, send them to whatever page you want
        HttpSession session = request.getSession();
        session.setAttribute("userName", userName);
        return new RedirectView("/recipe"); // <--- change to be recipe page
      }
      m.addAttribute("message", "Bad password");
      return new RedirectView("/");
    }
    m.addAttribute("message", "no user");
    return new RedirectView("/");
  }

  @GetMapping("/logout")
  public RedirectView logOutUser(HttpServletRequest request)
  {
    HttpSession session = request.getSession();
    session.invalidate();

    return new RedirectView("/");
  }
}
