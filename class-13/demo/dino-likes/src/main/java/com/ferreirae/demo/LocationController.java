package com.ferreirae.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LocationController {

    @Autowired
    LocationRepository locationRepo;
    @Autowired
    DinosaurRepository dinoRepo;

    @RequestMapping(value="/locations", method= RequestMethod.GET)
    public String index(Model m) {
        m.addAttribute("locations", locationRepo.findAll());
        return "locations";
    }

    @RequestMapping(value="/locations", method=RequestMethod.POST)
    public RedirectView create(@RequestParam String environment,
                               @RequestParam String name) {
        Location newLocation = new Location(environment, name);
        locationRepo.save(newLocation);
        return new RedirectView("/locations");
    }

    @RequestMapping(value="/locations/{locationId}/dinosaurs", method=RequestMethod.POST)
    public RedirectView addDinosaur(@PathVariable long locationId,
                                    @RequestParam String species,
                                    @RequestParam int numberOfSpikes,
                                    @RequestParam String diet) {
        Dinosaur newDino = new Dinosaur(species, numberOfSpikes, diet);
        newDino.location = locationRepo.findById(locationId).get();
        dinoRepo.save(newDino);
        return new RedirectView("/locations");

    }
}
