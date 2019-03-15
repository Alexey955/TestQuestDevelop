package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping("/employeeMain")
    public String employeeMain() {
        return "employeeMainPage";
    }


    @GetMapping("/lastChance")
    public String lastChance() {
        return "lastChancePage";
    }
}
