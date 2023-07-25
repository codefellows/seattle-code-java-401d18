package com.java401d18.salmonCookiesD18.controllers;

import com.java401d18.salmonCookiesD18.models.SalmonCookieStore;
import com.java401d18.salmonCookiesD18.repositories.SalmonCookieStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CookieController{
  // Step 6: Add an Autowired instance of repo to the controller
  @Autowired
  SalmonCookieStoreRepository salmonCookieStoreRepository;

  @GetMapping("/cookies")
  public String getCookiesPage() {
    return "cookies.html";
  }

  // localhost:8080/cookies/chocolate%20chip?cookieString=%20is%20yummy
  @GetMapping("/cookies/{cookieType}")
  public String getCookiesWithType(Model m,
                                   @PathVariable String cookieType,
                                   @RequestParam(required = false, defaultValue = " is my favorite") String cookieString) {
    m.addAttribute("cookieType", cookieType + cookieString);
    return "cookies.html";
  }

  @GetMapping("/stores")
  public String getStoresPage(Model m) {
    List<SalmonCookieStore> salmonCookieStores = salmonCookieStoreRepository.findAll();
    m.addAttribute("stores", salmonCookieStores);

    // grab a single store from our DB
    SalmonCookieStore reyStore = salmonCookieStoreRepository.findByName("rey's store");
    m.addAttribute("storeName", reyStore.getName());
    m.addAttribute("storeSales", reyStore.getAverageCookiesPerDay());
    return "salmon-cookies-stores.html";
  }

  @PostMapping("/stores/add")
  public RedirectView addStoreFromForm(String storeName, Integer averageSoldPerDay) {
    SalmonCookieStore newStore = new SalmonCookieStore(storeName, averageSoldPerDay);
    salmonCookieStoreRepository.save(newStore);

    return new RedirectView("/");
  }

  /** DELETE A SALMON COOKIE STORE
   *  in "salmon-cookies-stores.html" a delete button has been added to each form in the list
   *  pressing the button will trigger the delete mapping defined below
   *  NOTE: there is also an additional line added to application.properties to make this work
   **/
  @DeleteMapping("/stores/delete/{id}")
  public RedirectView deleteCookieStore(@PathVariable long id) {
    // Will get the id from th salmon cookie store being rendered in the template
    // deleteById() is a method that exists for free in our repository
    salmonCookieStoreRepository.deleteById(id);

    // on a successful delete, send the user back to the splash page
    return new RedirectView("/");
  }


  /** UPDATE A SALMON COOKIE STORE (2 parts)
   * 1) get mapping for a new page where our update form will live
   * 2) post mapping for the database update logic
   **/
  // get the webpage which contains a form for UPDATING a SalmonCookieStore's information
  @GetMapping("/stores/update")
  public String getUpdateStorePage() {
    return "salmon-cookie-update.html";
  }

  // the post mapping triggered by updating a store when submitting the form in "salmon-cookie-update.html"
  @PostMapping("/stores/update")
  public RedirectView updateStore(Long id, String name, Integer averageCookiesPerDay) {
    // grab the existing store from the database (if id doesn't exist, will return a null)
    // findById() is a method that exists for free in our repository
    // But it returns an Optional type... which holds data and is either null or not
    // We'll handle this Optional below
    Optional<SalmonCookieStore> storeFromRepo = salmonCookieStoreRepository.findById(id);

    // if there is an existing store with the given ID in the database, grab it to update it
    // otherwise, redirect the user back to the same page because we couldn't update the store they wanted
    if(storeFromRepo.isPresent()) {
      SalmonCookieStore storeToUpdate = storeFromRepo.get();
      // use the values from the form to update the store's information (if the value from the form isn't empty!)
      if(!name.equals("")) {
        storeToUpdate.setName(name);
      }

      if(averageCookiesPerDay != null) {
        storeToUpdate.setAverageCookiesPerDay(averageCookiesPerDay);
      }

      // after setting the new values, save the store to the database (will overwrite the changed values)
      salmonCookieStoreRepository.save(storeToUpdate);
    } else {
      return new RedirectView("/stores/update"); // <- this will trigger the getMapping for the given path
    }

    // if the update is successful, send the user back to the splash page
    return new RedirectView("/");
  }


}
