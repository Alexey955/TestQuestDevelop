package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Student;
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
    private StudentRepo studentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @GetMapping("/")
    public String decideUserOrAdmin(Model model) {

        List<User> studentList = userRepo.findAll();
        model.addAttribute("myDate", studentList);
        return "mainPage";
    }

    @GetMapping("/3")
    public String three(Model model) {

        departmentRepo.deleteById((long) 1);
        userRepo.deleteById((long) 1);
        return "mainPage";
    }

    @GetMapping("/4")
    public String four(Model model) {

        employeeRepo.deleteById((long) 1);
        userRepo.deleteById((long) 1);
        return "mainPage";
    }

}
