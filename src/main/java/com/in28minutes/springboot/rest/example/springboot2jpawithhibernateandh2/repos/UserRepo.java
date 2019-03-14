package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

//    void deleteById(int id);
}
