package com.alex.testQuest.repos;

import com.alex.testQuest.entities.Chief;
import com.alex.testQuest.entities.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChiefRepo extends CrudRepository<Chief, Long> {
    @Override
    List<Chief> findAll();

    Chief findFirstById(Long id);

    void deleteById(Long id);

    @Query(value = "select t.department from Chief t where t.id = ?1")
    Department findDepartmentById(Long id);
}
