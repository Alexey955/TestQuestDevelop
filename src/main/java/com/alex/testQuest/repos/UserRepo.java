package com.alex.testQuest.repos;

import com.alex.testQuest.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findFirstById(Long id);
}
