package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Person;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.User;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResultUser,
                          @Valid Person person, BindingResult bindingResultPerson,
                          Model model) {

        if(bindingResultUser.hasErrors() || bindingResultPerson.hasErrors()) {
            if(bindingResultUser.hasErrors() && bindingResultPerson.hasErrors()){
                ControllerUtils.addErrorToModelIfBindingResultError(bindingResultUser, model);
                ControllerUtils.addErrorToModelIfBindingResultError(bindingResultPerson, model);
                return "registrationPage";

            }else if(bindingResultUser.hasErrors()){
                ControllerUtils.addErrorToModelIfBindingResultError(bindingResultUser, model);
                return "registrationPage";

            }else {
                ControllerUtils.addErrorToModelIfBindingResultError(bindingResultPerson, model);
                return "registrationPage";
            }
        }

        if (!userService.addUser(user, person)) {
            model.addAttribute("usernameError", "User " + user.getUsername() +" exists.");
            return "registrationPage";
        }

        return "redirect:/login";
    }
}
