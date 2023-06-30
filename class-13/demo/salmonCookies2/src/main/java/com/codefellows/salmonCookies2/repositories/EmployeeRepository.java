package com.codefellows.salmonCookies2.repositories;

import com.codefellows.salmonCookies2.models.Employee;
import com.codefellows.salmonCookies2.models.SalmonCookiesStore;
import org.springframework.data.jpa.repository.JpaRepository;

// 4. Make a repository for that data value
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
}
