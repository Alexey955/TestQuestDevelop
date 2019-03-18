package com.alex.testQuest.repos;

import com.alex.testQuest.entities.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminRepo extends CrudRepository<Admin, Long> {
    @Override
    List<Admin> findAll();
}
