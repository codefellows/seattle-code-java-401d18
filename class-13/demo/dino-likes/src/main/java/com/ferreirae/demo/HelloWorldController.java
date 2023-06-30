package com.ferreirae.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

// uses templating!
@Controller
public class HelloWorldController {

    @RequestMapping("/")
    public RedirectView homeRedirect() {
        return new RedirectView("/dinosaurs");
    }
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
    @RequestMapping("/hello/{pineapple}/{fromPerson}")
    public String helloToYou(@PathVariable(name="pineapple") String myName,
                             @PathVariable String fromPerson,
                             Model model) {
        model.addAttribute("myName", myName);
        return "potato";
    }
}
