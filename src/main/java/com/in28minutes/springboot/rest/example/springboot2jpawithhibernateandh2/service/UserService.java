package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.service;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Department;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Employee;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.User;
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setRoles(Collections.singleton(Roles.EMPLOYEE));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        //Test
        Employee employee = new Employee();
        employee.setFirstName("Alex");
        Department department = new Department();
        department.setDepartmentName("DepName");
        department.addEmployee(employee);
        departmentRepo.save(department);
        //Test

        return true;
    }
}
