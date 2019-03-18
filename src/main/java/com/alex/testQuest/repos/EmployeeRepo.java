package com.alex.testQuest.repos;

import com.alex.testQuest.entities.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EmployeeRepo extends CrudRepository<Employee, Long> {
    @Override
    List<Employee> findAll();

    Employee findFirstById(Long id);
}
