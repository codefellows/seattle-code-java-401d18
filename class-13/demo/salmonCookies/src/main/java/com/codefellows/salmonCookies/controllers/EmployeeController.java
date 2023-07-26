package com.codefellows.salmonCookies.controllers;

import com.codefellows.salmonCookies.models.CookieStand;
import com.codefellows.salmonCookies.models.Employee;
import com.codefellows.salmonCookies.repositiories.CookieStandRepository;
import com.codefellows.salmonCookies.repositiories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EmployeeController {

    @Autowired
    CookieStandRepository cookieStandRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @PostMapping("/employee/add")
    public RedirectView addEmployee(String name, Integer payPerHour, String standName) {
        CookieStand stand = cookieStandRepository.findByName(standName);

        if(stand == null) {
            throw new IllegalArgumentException("Could not find stand with name " + standName);
        }

        Employee employee = new Employee(name, payPerHour, stand);
        employeeRepository.save(employee);

        return new RedirectView("/");

    }
}
