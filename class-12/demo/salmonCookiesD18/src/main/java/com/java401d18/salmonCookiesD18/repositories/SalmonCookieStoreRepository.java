package com.java401d18.salmonCookiesD18.repositories;

import com.java401d18.salmonCookiesD18.models.SalmonCookieStore;
import org.springframework.data.jpa.repository.JpaRepository;

// Step 4: create a repository that extends JpaRepository
public interface SalmonCookieStoreRepository extends JpaRepository<SalmonCookieStore, Long> {
  // default -> save(), delete(), update(), findAll()

  // Step 5: add MAGIC method declarations
  public SalmonCookieStore findByName(String name);
}
