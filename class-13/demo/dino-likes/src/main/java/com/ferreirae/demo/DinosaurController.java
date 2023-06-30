package com.ferreirae.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class DinosaurController {
    @Autowired
    private DinosaurRepository dinoRepo;

    @RequestMapping(value="/dinosaurs", method=RequestMethod.GET)
    public String index(Model m) {
        // grab all the dinosaurs from the database
//        Dinosaur[] dinosaurs = new Dinosaur[]{
////                new Dinosaur("stegosaurus", 45, "herbivore"),
////                new Dinosaur("velociraptor", 0, "carnivore")
////        };
        // display them on a page
        m.addAttribute("dinosaurs", dinoRepo.findAll());
        return "dinosaurIndex";
    }

    @RequestMapping(value="/dinosaurs", method=RequestMethod.POST)
    public RedirectView create(
            @RequestParam String species,
            @RequestParam int numberOfSpikes,
            @RequestParam String diet) {
        Dinosaur newDino = new Dinosaur(species, numberOfSpikes, diet);
        dinoRepo.save(newDino);
        return new RedirectView("/dinosaurs");
    }

    @RequestMapping(value="/dinosaurs/{id}/likes", method=RequestMethod.POST)
    public RedirectView addLike(@PathVariable long id, @RequestParam long likedDino) {
        Optional<Dinosaur> liker = dinoRepo.findById(id);
        Optional<Dinosaur> likee = dinoRepo.findById(likedDino);
        if(!liker.isPresent() || !likee.isPresent()) {
            // 404 redirect
            return null;
        } else {
            Dinosaur likerDino = liker.get();
            likerDino.dinosThatILike.add(likee.get());
            dinoRepo.save(likerDino);
            return new RedirectView("/dinosaurs");
        }
    }
}
