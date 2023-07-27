package com.recipesAuth.recipesAuth.controllers;

import com.recipesAuth.recipesAuth.models.SiteUser;
import com.recipesAuth.recipesAuth.repos.SiteUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {
  @Autowired
  SiteUserRepository siteUserRepository;

  @GetMapping("/recipe")
  public String getRecipe(HttpServletRequest request, Model m) {
    HttpSession session = request.getSession();


    // using a method from session... make sure you gate this behind an if statement
    Object userNameAttribute = session.getAttribute("userName");

    if (userNameAttribute == null) {
      return "redirect:/";
    }

    String userName = userNameAttribute.toString();

    //authenticate our user
    if(userName != null) {
      m.addAttribute("userName", userName);
      SiteUser siteUser = siteUserRepository.getSiteUserByUserName(userName);
      // m.addAttribute() <--- add all of your recipes
      return "recipes.html";
    }

    return "redirect:/";
  }
}
