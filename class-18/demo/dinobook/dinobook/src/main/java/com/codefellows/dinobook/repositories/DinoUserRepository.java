package com.codefellows.dinobook.repositories;

import com.codefellows.dinobook.model.DinoUser;
import org.springframework.data.jpa.repository.JpaRepository;

// Step 1B: Make user repository
public interface DinoUserRepository extends JpaRepository<DinoUser, Long>
{
    DinoUser findByUsername(String username);
}
