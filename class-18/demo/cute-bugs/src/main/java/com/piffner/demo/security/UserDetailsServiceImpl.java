package com.piffner.demo.security;

import com.piffner.demo.database.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implements the user details service.
 * Used to look up the username from the login form
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AppUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username);
    }
}
