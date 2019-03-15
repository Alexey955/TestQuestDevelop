package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String pickMainPage(@AuthenticationPrincipal User user) {

        if(user.getRoles().iterator().next().toString().equals("ADMIN")) {
            return "adminMainPage";

        } else if(user.getRoles().iterator().next().toString().equals("EMPLOYEE")){
            return "employeeMainPage";

        } else{
            return "chiefMainPage";
        }
    }
}
