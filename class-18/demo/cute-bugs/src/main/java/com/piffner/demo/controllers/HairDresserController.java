package com.piffner.demo.controllers;

import com.piffner.demo.BugNotFoundException;
import com.piffner.demo.database.Customer;
import com.piffner.demo.database.CustomerRepository;
import com.piffner.demo.database.HairDresser;
import com.piffner.demo.database.HairDresserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hairdressers")
public class HairDresserController {

    @Autowired
    HairDresserRepo repo;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/test")
    @ResponseBody
    public RedirectView test() {
        // we're going to create a hair dresser and save it out
        HairDresser hd = new HairDresser();
        hd.name = "Bob";
        repo.save(hd);

        // if the hair dresser already existed
        // we would have to get it first
        // (Getting the id from a RequestParam or PathVariable, etc.)
        // repo.findById(hairDresserId);

        // To attach the customer, we just need to set the hairDresser property
        // then save out the customer
        Customer fred = new Customer();
        fred.name = "Fred";
        fred.hairDresser = hd;
        customerRepository.save(fred);

        Customer george = new Customer();
        george.name = "George The George";
        george.hairDresser = hd;
        customerRepository.save(george);

        // note, we didn't have to add the customers to the HairDresser!
        // because the value is stored in the Customer object (mappedBy)

        // if you actually open up the DB, the Customer object has the reference to the HairDresser
        // while the HairDresser knows nothing about the customers.

        // Where the relationship is 'stored' is what makes the difference


        // for ease of use, let's just redirect to the new path
        return new RedirectView("/hairdressers/" + hd.id);
    }

    @GetMapping("/{id}")
    public String getSingle(
            @PathVariable Long id,
            Model model
    ) {

        Optional<HairDresser> hd = repo.findById(id);

        if (hd.isPresent()) {
            HairDresser actual = hd.get();

            // The customers are LAZY loaded, meaning until you access the property
            // hibernate doesn't query the database for them.
            // It's easier to debug these types of operations in the controller,
            // so force it to happen here
            List<Customer> customers = actual.customers;

            model.addAttribute("hairdresser", actual);
            model.addAttribute("customers", customers);

            return "hairdresser";
        }

        throw new BugNotFoundException();


    }
}
