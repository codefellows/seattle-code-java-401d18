package com.codellows.tirekicker.models;


import com.codellows.tirekicker.controllers.DinoUserController;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DinoUser implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String username;
  String password;

  // implement many to many!
  @ManyToMany
  @JoinTable (
    name = "followers_to_followees",
    joinColumns = {@JoinColumn(name = "userWhoIsFollowing")},
    inverseJoinColumns = {@JoinColumn(name = "FollowedUser")}
  ) // takes a name, first join column, and inverse join column
  Set<DinoUser> usersIFollow = new HashSet<>();

  @ManyToMany(mappedBy = "usersIFollow")
  Set<DinoUser> usersWhoFollowMe = new HashSet<>();

  public Set<DinoUser> getUsersIFollow() {
    return usersIFollow;
  }

  public void setUsersIFollow(Set<DinoUser> usersIFollow) {
    this.usersIFollow = usersIFollow;
  }

  public Set<DinoUser> getUsersWhoFollowMe() {
    return usersWhoFollowMe;
  }

  public void setUsersWhoFollowMe(Set<DinoUser> usersWhoFollowMe) {
    this.usersWhoFollowMe = usersWhoFollowMe;
  }

  public Long getId() {
    return id;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  String nickname;

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  LocalDate dateOfBirth;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
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
}
