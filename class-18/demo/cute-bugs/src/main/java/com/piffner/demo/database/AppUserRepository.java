package com.piffner.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    // Jpa will 'magic' up this just by adding a function named correctly
    // this allows us to search the AppUser's by username
    AppUser findByUsername(String username);
}
