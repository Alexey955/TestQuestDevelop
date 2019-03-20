package com.alex.testQuest.repos;

import com.alex.testQuest.entities.People;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeopleRepo extends CrudRepository<People, Long> {
    @Override
    List<People> findAll();
}
