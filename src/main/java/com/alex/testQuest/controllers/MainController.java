package com.alex.testQuest.controllers;

import com.alex.testQuest.entities.User;
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
