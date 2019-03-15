package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAuthority('EMPLIYEE')")
public class EmployeeController {

    @GetMapping("/employeeMain")
    public String employeeMain() {
        return "employeeMainPage";
    }

}
