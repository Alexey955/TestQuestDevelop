package com.alex.testQuest.service;

import com.alex.testQuest.entities.*;
import com.alex.testQuest.dto.Person;
import com.alex.testQuest.repos.DepartmentRepo;
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

        switch (person.getRole()) {
            case "Employee":
                user.setRoles(Collections.singleton(Roles.EMPLOYEE));
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepo.save(user);
                Employee employee = new Employee(user.getId(), person.getFirstName(), person.getLastName());

                if(department != null){
                    department.addEmployee(employee);
                }else {
                    department = new Department(user.getId(), person.getDepartmentName());
                    department.addEmployee(employee);
                }
                departmentRepo.save(department);
                break;

            case "Chief":
                user.setRoles(Collections.singleton(Roles.CHIEF));
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepo.save(user);
                Chief chief = new Chief(user.getId(), person.getFirstName(), person.getLastName());

                if(department != null){
                    department.addChief(chief);
                }else {
                    department = new Department(user.getId(), person.getDepartmentName());
                    department.addChief(chief);
                }
                departmentRepo.save(department);
                break;

            case "Admin":
                user.setRoles(Collections.singleton(Roles.ADMIN));
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepo.save(user);
                Admin admin = new Admin(user.getId(), person.getFirstName(), person.getLastName());
                adminRepo.save(admin);
                break;
        }

        return true;
    }
}
