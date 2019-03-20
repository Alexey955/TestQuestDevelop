package com.alex.testQuest.service;

import com.alex.testQuest.entities.*;
import com.alex.testQuest.dto.Person;
import com.alex.testQuest.repos.DepartmentRepo;
import com.alex.testQuest.repos.PeopleRepo;
import com.alex.testQuest.repos.UserRepo;
import com.alex.testQuest.roles.Roles;
//import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.entities.*;
import com.alex.testQuest.repos.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private PeopleRepo peopleRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user, Person person) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        Department department = departmentRepo.findByDepartmentName(person.getDepartmentName());
        People people;

        switch (person.getRole()){
            case "EMPLOYEE":
                user.setRoles(Collections.singleton(Roles.EMPLOYEE));
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepo.save(user);

                if(department != null){
                }else {
                    department = new Department();
                    department.setDepartmentName(person.getDepartmentName());
                }

                departmentRepo.save(department);

                people = new People();
                people.setFirstName(person.getFirstName());
                people.setLastName(person.getLastName());
                people.setUser(user);
                people.setDepartment(department);
                peopleRepo.save(people);
                break;

            case "CHIEF":
                user.setRoles(Collections.singleton(Roles.CHIEF));
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepo.save(user);

                if(department != null){
                }else {
                    department = new Department();
                    department.setDepartmentName(person.getDepartmentName());
                }

                departmentRepo.save(department);

                people = new People();
                people.setFirstName(person.getFirstName());
                people.setLastName(person.getLastName());
                people.setUser(user);
                people.setDepartment(department);
                peopleRepo.save(people);
                break;

            case "ADMIN":
                user.setRoles(Collections.singleton(Roles.ADMIN));
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepo.save(user);
                break;
        }

        return true;
    }
}
