package com.codefellows.salmonCookies.controllers;

import com.codefellows.salmonCookies.models.CookieStand;
import com.codefellows.salmonCookies.repositiories.CookieStandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class StandController {

    @Autowired
    CookieStandRepository cookieStandRepository;

    @GetMapping("/")
    public String getAllStands(Model model) {

        model.addAttribute("cookieStands", cookieStandRepository.findAll());
        return "cookie-stands.html";
    }

    @GetMapping("/{id}")
    public String getOneStand(Model model, @PathVariable Long id) {
        CookieStand stand = cookieStandRepository.getById(id);


        model.addAttribute("stand", stand);

        return "cookie-stand.html";
    }

    @PostMapping("/create")
    public RedirectView createStand(String name, Integer averageCookiesPerSale) {
        CookieStand stand = new CookieStand(name, averageCookiesPerSale);
        cookieStandRepository.save(stand);
        return new RedirectView("/");
    }

}
