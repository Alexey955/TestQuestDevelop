package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Chief;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Department;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Employee;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.User;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private ChiefRepo chiefRepo;

    @GetMapping("/")
    public String decideUserOrAdmin(Model model) {

        List<User> studentList = userRepo.findAll();
        model.addAttribute("myDate", studentList);
        return "mainPage";
    }

    @GetMapping("/1")
    public String one(Model model) {

        List<User> studentList = userRepo.findAll();
        List<Employee> employeeList = employeeRepo.findAll();
        List<Chief> chiefList = chiefRepo.findAll();
        List<Department> departmentList = departmentRepo.findAll();
        return "mainPage";
    }
}
