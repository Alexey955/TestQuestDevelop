package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.User;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model, @RequestParam String radioDel, @RequestParam int departmentId) {

        System.out.println("radioDel = " + radioDel);

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User " + user.getUsername() +" exists.");
            return "registrationPage";
        }

        return "redirect:/login";
    }
}
