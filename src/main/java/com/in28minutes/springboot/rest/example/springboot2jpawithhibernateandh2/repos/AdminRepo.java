package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminRepo extends CrudRepository<Admin, Long> {
    @Override
    List<Admin> findAll();
}
