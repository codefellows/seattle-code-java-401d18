package com.ferreirae.securedemo.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AppUserRepository userRepo;
    @Autowired
    private AuthenticationManager authManager;

    @GetMapping("/signup")
    public String showSignUpPage(Principal p, Model m) {
        System.out.println(p);
        AppUser u = new AppUser();
        m.addAttribute("user", u);
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String fullName) {
        AppUser newUser = new AppUser(username, bCryptPasswordEncoder.encode(password), fullName);
        newUser = userRepo.save(newUser);
        // maybe autologin?
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/myprofile")
    public String myProfile(Principal p, Model m) {
        //AppUser currentUser = (AppUser) p;
        System.out.println(p);
        m.addAttribute("user", ((UsernamePasswordAuthenticationToken) p).getPrincipal());
        return "profile";
    }

    @GetMapping("/users/{id}")
    public String userShow(@PathVariable long id, Model m) {
        Optional<AppUser> u = userRepo.findById(id);
        if(u.isPresent()) {
            m.addAttribute("user", u.get());
            return "profile";
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
// came from https://stackoverflow.com/questions/2066946/trigger-404-in-spring-mvc-controller
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends RuntimeException {

}
