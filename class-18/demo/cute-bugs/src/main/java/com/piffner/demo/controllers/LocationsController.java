package com.piffner.demo.controllers;

import com.piffner.demo.database.Location;
import com.piffner.demo.database.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Our locations controller controls our views for adding locations
 */
@Controller
@RequestMapping("/locations")
public class LocationsController {

    /**
     * Autowiring in a location repo the same way we do the bugs repo
     */
    @Autowired
    LocationRepository repo;

    // get locations
    @GetMapping
    public String getLocations(Model model) {

        List<Location> locations = this.repo.findAll();

        model.addAttribute("locations", locations);

        return "locations";
    }

    // get a location

    // create a location
    @PostMapping("/create")
    public RedirectView createLocation(
            @RequestParam String name,
            @RequestParam int maxOccupancy,
            @RequestParam String terrainType
//            @RequestParam boolean hasFoliage
    ) {
        Location location = new Location();
        location.name = name;
        location.maxOccupancy = maxOccupancy;
        location.terrainType = terrainType;
//        location.hasFoliage = hasFoliage;

        location = repo.save(location);

        // redirect puts us back at the /locations root
        // we could also RedirectView("/bugfarm") if we
        // wanted to go to a different controller
        return new RedirectView("/locations");
    }

    // update a location

    // delete a location
}
