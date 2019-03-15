package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Chief;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Department;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Employee;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.User;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String decideUserOrAdmin(@AuthenticationPrincipal User user, Model model) {

        if(user.getRoles().iterator().next().toString().equals("ADMIN")) {
            return "adminMainPage";

        } else if(user.getRoles().iterator().next().toString().equals("EMPLOYEE")){
            return "employeeMainPage";

        } else{
            return "chiefMainPage";
        }
    }
}
