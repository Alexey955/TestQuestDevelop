package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.service;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.*;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.AdminRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.DepartmentRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.EmployeeRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.UserRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.roles.Roles;
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
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user, String role, String firstName, String lastName, String departmentName) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        Department department = departmentRepo.findByDepartmentName(departmentName);

        switch (role) {
            case "Employee":
                user.setRoles(Collections.singleton(Roles.EMPLOYEE));
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepo.save(user);
                Employee employee = new Employee(user.getId(), firstName, lastName);

                if(department != null){
                    department.addEmployee(employee);
                }else {
                    department = new Department(user.getId(), departmentName);
                    department.addEmployee(employee);
                }
                departmentRepo.save(department);
                break;

            case "Chief":
                user.setRoles(Collections.singleton(Roles.CHIEF));
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepo.save(user);
                Chief chief = new Chief(user.getId(), firstName, lastName);

                if(department != null){
                    department.addChief(chief);
                }else {
                    department = new Department(user.getId(), departmentName);
                    department.addChief(chief);
                }
                departmentRepo.save(department);
                break;

            case "Admin":
                user.setRoles(Collections.singleton(Roles.ADMIN));
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepo.save(user);
                Admin admin = new Admin(user.getId(), firstName, lastName);
                adminRepo.save(admin);
                break;
        }

        return true;
    }
}
