package com.codefellows.dinobook.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

// Step 1A: Make user model (NOT called "User"!)
@Entity
// Step 4: Implement UserDetails
public class DinoUser implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String nickname;
    private String testTextArea;

    // Don't cascade here! You don't need it; the join table will update when users are removed
    @ManyToMany(mappedBy = "employees")
    Set<DinoUser> managers;

    // Don't cascade here! You don't need it; the join table will update when users are removed
    @ManyToMany
    @JoinTable(
        name = "dinomanagers_to_dinoemployees",  // table name
        joinColumns = {@JoinColumn(name="manager")},
        inverseJoinColumns = {@JoinColumn(name="employee")}
    )
    Set<DinoUser> employees;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getTestTextArea()
    {
        return testTextArea;
    }

    public void setTestTextArea(String testTextArea)
    {
        this.testTextArea = testTextArea;
    }

    public Set<DinoUser> getManagers()
    {
        return managers;
    }

    public Set<DinoUser> getEmployees()
    {
        return employees;
    }
}
