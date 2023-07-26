package com.codefellows.salmonCookies.repositiories;

import com.codefellows.salmonCookies.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> { }
