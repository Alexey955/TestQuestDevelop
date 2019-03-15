package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepo extends CrudRepository<Department, Long> {
    @Override
    List<Department> findAll();

    Department findByDepartmentName(String departmentName);

    Department findFirstById(Long id);

    @Query(value = "select t.departmentName from Department t where t.id = ?1")
    String findDepartmentNameById(Long id);

    @Query(value = "select max(t.departmentName) from Department t")
    Long findMaxId();
}
