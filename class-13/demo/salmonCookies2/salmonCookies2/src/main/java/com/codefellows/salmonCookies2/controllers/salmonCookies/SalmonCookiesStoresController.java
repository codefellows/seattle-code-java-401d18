package com.codefellows.salmonCookies2.controllers.salmonCookies;

import com.codefellows.salmonCookies2.models.SalmonCookiesStore;
import com.codefellows.salmonCookies2.repositories.SalmonCookiesStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SalmonCookiesStoresController
{
    // 5. Wire up a repository
    @Autowired
    SalmonCookiesStoreRepository salmonCookiesStoreRepository;

    @GetMapping("/")
    public String getSalmonCookiesStores(Model m)
    {
        List<SalmonCookiesStore> stores = salmonCookiesStoreRepository.findAll();
        m.addAttribute("stores", stores);

        return "salmon-cookies/salmon-cookies-stores";
    }

    @PostMapping("/")
    public RedirectView createSalmonCookieStore(String name, int averageCookiesPerDay)
    {
        SalmonCookiesStore newStore = new SalmonCookiesStore(name, averageCookiesPerDay);
        salmonCookiesStoreRepository.save(newStore);

        return new RedirectView("/");
    }

    @DeleteMapping("/delete")
    public RedirectView deleteSalmonCookieStore()
    {
        // TODO: Implement me as a stretch goal if you want
        return new RedirectView("/");
    }
}
