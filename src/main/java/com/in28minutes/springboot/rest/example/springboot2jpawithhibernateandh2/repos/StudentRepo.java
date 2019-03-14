package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface StudentRepo extends CrudRepository<Student, Integer> {
    @Override
    List<Student> findAll();
}