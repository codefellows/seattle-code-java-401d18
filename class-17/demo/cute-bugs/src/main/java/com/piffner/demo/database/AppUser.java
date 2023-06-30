package com.piffner.demo.database;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    private boolean isAdmin;

    public long getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();

        if (this.username.equals("admin")) {  // or isAdmin?
            list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return list;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
