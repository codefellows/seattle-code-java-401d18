package com.piffner.demo.database;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

/**
 * Wraps a user object to store in the database
 * Implements UserDetails for use in the UserDetailsService
 */
@Entity
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue
    private long id;

    /**
     * Don't allow the same username twice
     */
    @Column(unique = true)
    private String username;

    private String password;
    private String favoriteQueen;

    public long getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFavoriteQueen() {
        return favoriteQueen;
    }

    public void setFavoriteQueen(String favoriteQueen) {
        this.favoriteQueen = favoriteQueen;
    }
}
