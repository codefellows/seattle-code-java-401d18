package com.java401d18.salmonCookiesD18.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeachingController {
  @GetMapping("/")
  public String getSplashPage() {
    return "splash.html";
  }

  @ResponseBody
  @GetMapping("/hello-world")
  public String getHelloWorld() {
    return "Hello World";
  }

  @GetMapping("/bff")
  public String getBffPage(Model m) {
    m.addAttribute("name", "Indi");
    m.addAttribute("years", "2");
    m.addAttribute("occupation", "baker");
    return "bff.html";
  }
}
