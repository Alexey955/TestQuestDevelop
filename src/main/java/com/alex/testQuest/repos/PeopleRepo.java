package com.alex.testQuest.repos;

import com.alex.testQuest.entities.Department;
import com.alex.testQuest.entities.People;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeopleRepo extends CrudRepository<People, Long> {
    @Override
    List<People> findAll();

    @Query(value = "select t from People t where t.department = ?1")
    List<People> findAllByDepartment(Department department);
}
