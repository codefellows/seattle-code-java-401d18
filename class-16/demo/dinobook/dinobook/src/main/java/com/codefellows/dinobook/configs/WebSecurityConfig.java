package com.codefellows.dinobook.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Step 5: Make WebSecurityConfig extends WebSecurityConfigurerAdapter
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    // Step 5A: Wire up a userDetailsService
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // Step 5B: Wire up a reusable passwordEncoder
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    // Step 5C: Link the userDetailsService and the passwordEncoder via a configure() method
    @Override
    public void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception
    {
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // Step 5D: Configure the security of our application via HttpSecurity
    // This is where we will do the work of how users can access the site
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
            .cors().disable()
            .csrf().disable()  // CSRF: submitting requests blindly to the server as you (meaning using your cookies)
            .authorizeRequests()
                .antMatchers("/").permitAll()  // allow all users to have access to this
                .antMatchers("/userInfo", "/login", "/signup").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")// any unauthorized request goes here
            .and()
                .logout()
                .logoutSuccessUrl("/login");
    }

}
