package com.java401d18.salmonCookiesD18.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CookieController{
  @GetMapping("/cookies")
  public String getCookiesPage() {
    return "cookies.html";
  }

  // /cookies/chocolate%20chip
  // @GetMapping("/cookies/{cookieType}")
  // public String getCookiesWithType(Model m, @PathVariable String cookieType) {
  //   m.addAttribute("cookieType", cookieType);
  //   System.out.println(cookieType); // <- dev testing purposes only!
  //   return "cookies.html";
  // }

  // localhost:8080/cookies/chocolate%20chip?cookieString=%20is%20yummy
  @GetMapping("/cookies/{cookieType}")
  public String getCookiesWithType(Model m,
                                   @PathVariable String cookieType,
                                   @RequestParam(required = false, defaultValue = " is my favorite") String cookieString) {
    m.addAttribute("cookieType", cookieType + cookieString);
    return "cookies.html";
  }

}
