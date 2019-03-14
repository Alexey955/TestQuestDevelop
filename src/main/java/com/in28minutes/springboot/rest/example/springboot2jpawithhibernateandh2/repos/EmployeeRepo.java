package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EmployeeRepo extends CrudRepository<Employee, Long> {
    @Override
    List<Employee> findAll();
}
