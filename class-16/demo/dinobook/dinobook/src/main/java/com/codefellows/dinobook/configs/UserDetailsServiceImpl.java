package com.codefellows.dinobook.configs;

import com.codefellows.dinobook.model.DinoUser;
import com.codefellows.dinobook.repositories.DinoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Step 3: Make a UserDetailsServiceImpl that implements UserDetailsService
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    DinoUserRepository dinoUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return dinoUserRepository.findByUsername(username);
    }
}
