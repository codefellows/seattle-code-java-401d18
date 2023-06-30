package com.piffner.demo.controllers;

import com.piffner.demo.BugNotFoundException;
import com.piffner.demo.database.Bug;
import com.piffner.demo.database.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// A @RestController is _almost_ identical to a regular @Controller
// It simply changes the defaults to @ResponseBody for all methods.
@RestController
@RequestMapping("/bugs")
public class BugController {

    // Magics up a repository to work with the database
    @Autowired
    BugRepository repo;

    @GetMapping("/")
    public Iterable<Bug> getBugs() {
        // create a bug
        Bug bug = new Bug();
        bug.name = "Itch";
        bug.cutenessScale = 4;
        bug.numberOfLegs = 6;
        bug.typeOfBug = "Flea";

        // Save inserts or updates a bug
        // Because there is no "id" set, it will insert
        this.repo.save(bug);

        // returns all the bugs (including the new one)
        Iterable<Bug> bugs = this.repo.findAll();

        // because this is a RestController, everything will be turned
        // into json
        return bugs;
    }

    @GetMapping("/bug/{id}")
    public Bug getBug(
            @PathVariable Long id
    ) {
        // Get a bug by id
        // Will return an "Optional<>" which just wraps the bug
        // so you can check if it exists or not
        Optional<Bug> bug = this.repo.findById(id);
        if (bug.isPresent()) {
            // return the value of the optional
            return bug.get();
        } else {
            // or throw an error!
            // (Checkout the BugNotFoundException class
            // to see how it is implemented)
            throw new BugNotFoundException();
        }
    }

    @PostMapping("/bug")
    public Bug createBug(
            // @RequestBody is the opposite of @ResponseBody
            // @ResponseBody changes it to json
            // @RequestBody changes json into an object!
            @RequestBody Bug bug
    ) {
        // We received an entire bug!  And just saving it out
        // note:  We're allowing them to set the id!
        // this is bad:  See the updateBug() for the better way
        bug = this.repo.save(bug);

        // return the resulting bug - it will have the ID set
        return bug;
    }

    @PutMapping("/bug/{id}")
    public Bug updateBug(
            @PathVariable Long id,
            @RequestBody Bug bug
    ) {
        // Make sure the bug exists first
        // we do this to verify that they aren't passing in an id like 99999
        // and .save() would just save it out (bad bug)
        Optional<Bug> repoBug = this.repo.findById(id);

        // if it does, then update it
        if (repoBug.isPresent()) {
            Bug foundBug = repoBug.get();

            foundBug.name = bug.name;
            foundBug.typeOfBug = bug.typeOfBug;
            foundBug.numberOfLegs = bug.numberOfLegs;
            foundBug.cutenessScale = bug.cutenessScale;

            foundBug = this.repo.save(foundBug);
            return foundBug;
        }

        // if it doesn't exist, not found exception
        throw new BugNotFoundException();
    }

    @DeleteMapping("/bug/{id}")
    public void deleteBug(
            @PathVariable Long id
    ) {
        // delete doesn't care if it exists or not.
        // so we can just pass it in
        this.repo.deleteById(id);

        // since there is a void return, it'll just be a HTTP 200
    }

}
