package com.piffner.demo.controllers;

import com.piffner.demo.BugNotFoundException;
import com.piffner.demo.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

/**
 * Our bugfarm controller is for the 'view' of our app
 * <p>
 * A more 'traditional' type controller than the RestController (used for api's)
 */
@Controller
@RequestMapping("/bugs")
public class BugFarmController {

    // we can wire in multiple repositories

    @Autowired
    BugRepository repo;

    @Autowired
    LocationRepository localRepo;

    @GetMapping("/dosomething")
    public RedirectView doSomethingWithAUser(@AuthenticationPrincipal AppUser user) {

        // only allow a user to edit themself!
        String username = user.getUsername();

        Bug bug = repo.findByName(user.getFavoriteQueen());

        return new RedirectView("/bugfarm/bug/" + bug.id);
    }

    // list bugs
    @GetMapping
    public String getBugs(Model model) {
        List<Bug> bugs = repo.findAll();
        List<Location> locations = localRepo.findAll();

        model.addAttribute("bugs", bugs);
        model.addAttribute("locations", locations);

        return "bugs";
    }

    // create a bug
    @PostMapping("/create")
    public RedirectView createLocation(
            @RequestParam String name,
            @RequestParam int numberOfLegs,
            @RequestParam int cutenessScale,
            @RequestParam String typeOfBug
    ) {
        Bug bug = new Bug();
        bug.name = name;
        bug.numberOfLegs = numberOfLegs;
        bug.cutenessScale = cutenessScale;
        bug.typeOfBug = typeOfBug;

        System.out.println(bug.id);  // bug.id = 0
        bug = repo.save(bug);
        System.out.println(bug.id); // bug.id = db id

        return new RedirectView("/bugfarm");
    }

    @GetMapping("/bug/{id}")
    public String getBug(
            @PathVariable Long id,
            Model model
    ) {

        Optional<Bug> foundBug = repo.findById(id);

        if (foundBug.isPresent()) {
            List<Location> locations = localRepo.findAll();
            model.addAttribute("locations", locations);
            model.addAttribute("bug", foundBug.get());
            return "bug";
        }

        throw new BugNotFoundException();
    }

    @PostMapping("/update")
    public RedirectView updateBug(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam int numberOfLegs,
            @RequestParam int cutenessScale,
            @RequestParam String typeOfBug,
            @RequestParam long location
    ) {

        // we have to get the bug from the passed in id
        Optional<Bug> foundBug = repo.findById(id);

        // _and_ the location we want to connect it to from the passed in location id
        Optional<Location> foundLocation = localRepo.findById(location);

        if (foundBug.isPresent() && foundLocation.isPresent()) {
            // if they both exist, we can set everything
            Bug bug = foundBug.get();
            Location local = foundLocation.get();
            bug.name = name;
            bug.numberOfLegs = numberOfLegs;
            bug.cutenessScale = cutenessScale;
            bug.typeOfBug = typeOfBug;
            // we assign the bug location simply by setting the location
            bug.location = local;
            // and saving it out
            bug = repo.save(bug);
        } else {
            throw new BugNotFoundException();
        }

        return new RedirectView("/bugfarm");
    }
    // assign a bug to a location

    // update

    // delete
}
