package com.piffner.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Configuration largely from: https://www.baeldung.com/spring-security-login

/**
 * Configures Spring's web security.
 * <p>
 * Configuration annotation tells spring to load this and check for 'beans'
 * EnableWebSecurity tells spring that this configures security
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Wire in the user details to make it available to the auth manager below
     */
    @Autowired
    UserDetailsService service;

    /**
     * A password encoder for use with the AccountController
     * and auth manager.
     *
     * @return
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    /**
     * Configure the auth manager used to validate logins
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder encoder = getPasswordEncoder();

        auth
                .userDetailsService(this.service)
                .passwordEncoder(encoder);
    }

    /**
     * Configure the paths and such for the security
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()

                // setup authorized pages
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/login*").permitAll()
                .antMatchers("/signup*").permitAll()
                .antMatchers("/style.css").permitAll()
                .anyRequest().authenticated()

                // configure login
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/bugfarm")
                .failureUrl("/login-error")

                // configure logout
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
        ;
    }
}
