package com.codefellows.dinobook.controller;

import com.codefellows.dinobook.model.DinoUser;
import com.codefellows.dinobook.repositories.DinoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        // use this to trigger a 404 error, see exception below this function
        //throw new ResourceNotFoundException("It's a 404");

        return "index.html";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException
    {
         ResourceNotFoundException(String message)
         {
             super(message);
         }
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

        m.addAttribute("testDate", LocalDateTime.now());

        return "/user-info.html";
    }

    @PutMapping("/users/{id}")
    public RedirectView editUserInfo(Model m, Principal p, @PathVariable Long id, String username, String nickname, RedirectAttributes redir)
    {
        if ((p != null) && (p.getName().equals(username)))
        {
            DinoUser dinoUser = dinoUserRepository.findById(id).orElseThrow();
            dinoUser.setUsername(username);
            dinoUser.setNickname(nickname);
            dinoUserRepository.save(dinoUser);
        }
        else
        {
            redir.addFlashAttribute("errorMessage", "Cannot edit another user's page!");
        }

        return new RedirectView("/users/" + id);
    }
}
