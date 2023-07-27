package com.recipesAuth.recipesAuth.repos;

import com.recipesAuth.recipesAuth.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
  public SiteUser findSiteUserByUserName(String userName);
}
