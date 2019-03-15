package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Chief;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChiefRepo extends CrudRepository<Chief, Integer> {
    @Override
    List<Chief> findAll();

    Chief findFirstById(Long id);

    void deleteById(Long id);
}
