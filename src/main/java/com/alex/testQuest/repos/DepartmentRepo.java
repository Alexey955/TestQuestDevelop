package com.alex.testQuest.repos;

import com.alex.testQuest.entities.Department;
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
}
