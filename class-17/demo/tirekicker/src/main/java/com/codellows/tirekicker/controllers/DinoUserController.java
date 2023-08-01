package com.codellows.tirekicker.controllers;

import com.codellows.tirekicker.models.DinoUser;
import com.codellows.tirekicker.repositories.DinoUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    return new RedirectView("/");
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

    // use this to trigger an 404 error, see exception below this function tpo create custom exception
    // throw new  ResourceNotFoundException("It's a 404");

    return "index.html";
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public class ResourceNotFoundException extends RuntimeException {
    ResourceNotFoundException(String message) {
      super(message);
    }
  }

  @GetMapping("/test")
  public String getTestPage(Principal p, Model m) {
    if(p != null) { // not strictly required if your WebSecurityConfig is correct
      String username = p.getName();
      DinoUser dinoUser = dinoUserRepository.findByUsername(username);

      m.addAttribute("username", username);
      m.addAttribute("nickname", dinoUser.getNickname());
    }

    return "/test.html";
  }

  @GetMapping("/users/{id}")
  public String getUserInfo(Model m, Principal p, @PathVariable Long id) {
    if (p != null)  // not strictly required if your WebSecurityConfig is correct
    {
      String username = p.getName();
      DinoUser dinoBrowsingUser = dinoUserRepository.findByUsername(username);

      m.addAttribute("browsingUserUsername", username);
      m.addAttribute("browsingUserNickname", dinoBrowsingUser.getNickname());
    }

    DinoUser dinoUser = dinoUserRepository.findById(id).orElseThrow();
    m.addAttribute("dinoUserUsername", dinoUser.getUsername());
    m.addAttribute("dinoUserNickname", dinoUser.getNickname());
    m.addAttribute("dinoUserId", dinoUser.getId());

    m.addAttribute("testDate", LocalDateTime.now());

    return "/user-info.html";
  }

  @PutMapping("/users/{id}")
  // Error Fragment: "RedirectAttributes redir" added to arguments to allow for adding error fragment to screen
  public RedirectView edituserInfo(Model m, Principal p, @PathVariable Long id, String username, String nickname,
                                   RedirectAttributes redir) {
    /** Error Fragment: server side check to make sure a user can only edit their own info
      * when a user takes an unauthorized action, disaply the error message on the page
      * NOTE: the "edit user info" button is disabled in the "user-info.html" template
      * to test the flashAttribute update, inspect page elements and manually delete
      * "hidden"property on the submit button
     **/
    if(p != null && (p.getName().equals(username))) {
      DinoUser dinoUser = dinoUserRepository.findById(id).orElseThrow();
      dinoUser.setUsername(username);
      dinoUser.setNickname(nickname);
      dinoUserRepository.save(dinoUser);
    } else {
      // Error Fragment: "addFlashAttribute" adds new attributes to the webpage on a reload
      // since "errorMessage" was previously null and now has a value, the Error Fragment will now display
      redir.addFlashAttribute("errorMessage", "Cannot edit another user's page!");
    }

    return new RedirectView("/users/" + id);
  }

}
