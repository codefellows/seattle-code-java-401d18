package com.codefellows.salmonCookies2.repositories;

import com.codefellows.salmonCookies2.models.SalmonCookiesStore;
import org.springframework.data.jpa.repository.JpaRepository;

// 4. Make a repository for that data value
public interface SalmonCookiesStoreRepository extends JpaRepository<SalmonCookiesStore, Long>
{
    // DARK MAGIC that we made happen with a specific function name
    public SalmonCookiesStore findByName(String name);
}
