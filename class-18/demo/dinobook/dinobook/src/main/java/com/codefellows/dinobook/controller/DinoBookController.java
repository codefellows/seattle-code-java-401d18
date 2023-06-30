package com.codefellows.dinobook.controller;

import com.codefellows.dinobook.model.DinoUser;
import com.codefellows.dinobook.repositories.DinoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class DinoBookController
{
    @Autowired
    DinoUserRepository dinoUserRepository;

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

    @GetMapping("/test")
    public String getTestPage(Principal p, Model m)
    {
        if (p != null)  // not strictly required if your WebSecurityConfig is correct
        {
            String username = p.getName();
            DinoUser dinoUser = dinoUserRepository.findByUsername(username);

            m.addAttribute("username", username);
            m.addAttribute("nickname", dinoUser.getNickname());
        }

        return "/test.html";

        // Uncomment this (and comment out the above code) to see the exception message on the error page
        //throw new RuntimeException("This is my error");
    }

    @GetMapping("/users/{id}")
    public String getUserInfo(Model m, Principal p, @PathVariable Long id)
    {
        if (p != null)  // not strictly required if your WebSecurityConfig is correct
        {
            String username = p.getName();
            DinoUser dinoBrowsingUser = dinoUserRepository.findByUsername(username);

            m.addAttribute("username", username);
            m.addAttribute("nickname", dinoBrowsingUser.getNickname());
        }

        DinoUser dinoUser = dinoUserRepository.findById(id).orElseThrow();
        m.addAttribute("dinoUserUsername", dinoUser.getUsername());
        m.addAttribute("dinoUserNickname", dinoUser.getNickname());
        m.addAttribute("dinoUserId", dinoUser.getId());
        m.addAttribute("dinoUserTestTextArea", dinoUser.getTestTextArea());
        m.addAttribute("dinoUserManagers", dinoUser.getManagers());
        m.addAttribute("dinoUserEmployees", dinoUser.getEmployees());

        m.addAttribute("testDate", LocalDateTime.now());

        return "/user-info.html";
    }

    @PutMapping("/users/{id}")
    public RedirectView editUserInfo(Model m, Principal p, @PathVariable Long id, String username, String nickname, String testTextArea, RedirectAttributes redir)
    {
        if ((p != null) && (p.getName().equals(username)))
        {
            DinoUser dinoUser = dinoUserRepository.findById(id).orElseThrow();
            dinoUser.setUsername(username);
            dinoUser.setNickname(nickname);
            dinoUser.setTestTextArea(testTextArea);
            dinoUserRepository.save(dinoUser);
        }
        else
        {
            redir.addFlashAttribute("errorMessage", "Cannot edit another user's page!");
        }

        return new RedirectView("/users/" + id);
    }

    @DeleteMapping("/users/{id}")
    public RedirectView deleteUser(@PathVariable Long id)
    {
        DinoUser dinoUser = dinoUserRepository.findById(id).orElseThrow();
        dinoUserRepository.delete(dinoUser);

        return new RedirectView("/");
    }

    @PutMapping("manage-user/{managedDinoId}")
    public RedirectView manageUser(Principal p, @PathVariable Long managedDinoId)
    {
        if (p == null)
        {
            throw new IllegalArgumentException("User must be logged in to manage other users!");
        }

        DinoUser browsingDino = dinoUserRepository.findByUsername(p.getName());
        DinoUser managedDino = dinoUserRepository.findById(managedDinoId).orElseThrow();

        browsingDino.getEmployees().add(managedDino);
        dinoUserRepository.save(browsingDino);

        return new RedirectView("/users/" + managedDinoId);
    }

    @PutMapping("employee-of-user/{toBeEmployedDinoId}")
    public RedirectView employUser(Principal p, @PathVariable Long toBeEmployedDinoId)
    {
        if (p == null)
        {
            throw new IllegalArgumentException("User must be logged in to employ other users!");
        }

        DinoUser browsingDino = dinoUserRepository.findByUsername(p.getName());
        DinoUser toBeEmployedDino = dinoUserRepository.findById(toBeEmployedDinoId).orElseThrow();

        browsingDino.getEmployees().add(toBeEmployedDino);
        dinoUserRepository.save(browsingDino);

        return new RedirectView("/users/" + toBeEmployedDinoId);
    }
}
