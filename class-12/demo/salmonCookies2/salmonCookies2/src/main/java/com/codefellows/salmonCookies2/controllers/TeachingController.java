package com.codefellows.salmonCookies2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TeachingController
{
    @GetMapping("/hello-world")
    public String helloWorldHappensHere()
    {
        System.out.println("Hello World!");
        System.out.println("Another thing");
        return "teaching/hello-world";  // goes to hello-world.html
    }

    @GetMapping("/bff")
    public String bestFriendsForeverPage(Model goofyModelName)
    {
        goofyModelName.addAttribute("bestFriendName", "Chris");
        goofyModelName.addAttribute("bestFriendYears", 15);
        goofyModelName.addAttribute("job", "programmer");
        return "teaching/best-friend.html";
    }

    @GetMapping("/bff2/{bffName}")
    public String bestFriendsForeverPageWithPathVariable(Model m, @PathVariable String bffName)
    {
        m.addAttribute("bestFriendName", bffName);
        m.addAttribute("bestFriendYears", 15);
        m.addAttribute("job", "programmer");
        return "teaching/best-friend.html";
    }

    @GetMapping("/bff3")  // but calling this with "?bffNameParam=MYBFFNAME"
    public String bestFriendsForeverPageWithQueryParam(Model m, String bffNameParam)
    {
        if (bffNameParam != null)
        {
            m.addAttribute("bestFriendName", bffNameParam);
        }
        else
        {
            m.addAttribute("bestFriendName", "John");
        }
        m.addAttribute("bestFriendYears", 15);
        m.addAttribute("job", "programmer");
        return "teaching/best-friend.html";
    }
}
