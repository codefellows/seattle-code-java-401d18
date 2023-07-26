package com.codefellows.salmonCookies.repositiories;


import com.codefellows.salmonCookies.models.CookieStand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookieStandRepository extends JpaRepository<CookieStand, Long>{
    public CookieStand findByName(String name);
}