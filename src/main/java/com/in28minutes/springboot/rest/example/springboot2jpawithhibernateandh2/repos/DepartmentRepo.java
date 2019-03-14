package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepo extends CrudRepository<Department, Long> {
    @Override
    List<Department> findAll();
}
