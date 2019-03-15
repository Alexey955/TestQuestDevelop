package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Chief;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChiefRepo extends CrudRepository<Chief, Integer> {
    @Override
    List<Chief> findAll();

    Chief findFirstById(Long id);

    void deleteById(Long id);

    @Query(value = "select t.department from Chief t where t.id = ?1")
    Department findDepartmentById(Long id);
}
