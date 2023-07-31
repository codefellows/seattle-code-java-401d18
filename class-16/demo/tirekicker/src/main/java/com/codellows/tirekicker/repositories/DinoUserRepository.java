package com.codellows.tirekicker.repositories;

import com.codellows.tirekicker.models.DinoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DinoUserRepository extends JpaRepository<DinoUser, Long> {
  public DinoUser findByUsername(String username);
}
