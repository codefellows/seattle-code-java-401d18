package com.codefellows.salmonCookies2.controllers.salmonCookies;

import com.codefellows.salmonCookies2.models.Employee;
import com.codefellows.salmonCookies2.models.SalmonCookiesStore;
import com.codefellows.salmonCookies2.repositories.EmployeeRepository;
import com.codefellows.salmonCookies2.repositories.SalmonCookiesStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController
{
    @Autowired
    SalmonCookiesStoreRepository salmonCookiesStoreRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/add-employee")
    RedirectView addEmployeeToStore(String employeeName, String salmonStoreName)
    {
        SalmonCookiesStore salmonCookiesStore = salmonCookiesStoreRepository.findByName(salmonStoreName);
        Employee newEmployee = new Employee(employeeName, salmonCookiesStore);
        employeeRepository.save(newEmployee);

        return new RedirectView("/");
    }
}
