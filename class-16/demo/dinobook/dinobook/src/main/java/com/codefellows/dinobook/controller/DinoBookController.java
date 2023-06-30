package com.codefellows.dinobook.controller;

import com.codefellows.dinobook.model.DinoUser;
import com.codefellows.dinobook.repositories.DinoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class DinoBookController
{
    @Autowired
    DinoUserRepository dinoUserRepository;

    // Step 8: Make a home page
    @GetMapping("/")
    public String getHomePage(Principal p, Model m)
    {
        if (p != null)
        {
            String username = p.getName();
            DinoUser dinoUser = dinoUserRepository.findByUsername(username);

            m.addAttribute("username", username);
            m.addAttribute("nickname", dinoUser.getNickname());
        }

        return "index.html";
    }
}
