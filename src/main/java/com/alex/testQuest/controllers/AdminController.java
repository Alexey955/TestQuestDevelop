package com.alex.testQuest.controllers;

import com.alex.testQuest.dto.Person;
import com.alex.testQuest.entities.People;
import com.alex.testQuest.entities.User;
import com.alex.testQuest.repos.DepartmentRepo;
import com.alex.testQuest.repos.PeopleRepo;
import com.alex.testQuest.repos.UserRepo;
import com.alex.testQuest.service.UserService;
import com.alex.testQuest.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("pickedPeople")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {


    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PeopleRepo peopleRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/adminMain")
    public String adminMain() {
        return "adminMainPage";
    }

    @GetMapping("/showUserList")
    public String showUserList(Model model) {

        List<Person> personList = new ArrayList<>();
        List<People> peopleList = peopleRepo.findAll();

        for(People people: peopleList){
            Person person = new Person(people.getId(), people.getFirstName(), people.getLastName());
            String departmentName = departmentRepo.findDepartmentNameById(people.getDepartment().getId());
            person.setDepartmentName(departmentName);
            person.setRole(people.getUser().getRoles().toString());

            personList.add(person);
        }

        model.addAttribute("personList", personList);

        return "userListPage";
    }

    @GetMapping("/createUser")
    public String createUser() {
        return "createUserPage";
    }

    @PostMapping("/registrationByAdmin")
    public String addUser(@Valid User user, BindingResult bindingResultUser,
                          @Valid Person person, BindingResult bindingResultPerson,
                          Model model) {

        if(bindingResultUser.hasErrors() || bindingResultPerson.hasErrors()) {
            if(bindingResultUser.hasErrors() && bindingResultPerson.hasErrors()){
                ControllerUtils.addErrorToModelIfBindingResultError(bindingResultUser, model);
                ControllerUtils.addErrorToModelIfBindingResultError(bindingResultPerson, model);
                return "createUserPage";

            }else if(bindingResultUser.hasErrors()){
                ControllerUtils.addErrorToModelIfBindingResultError(bindingResultUser, model);
                return "createUserPage";

            }else {
                ControllerUtils.addErrorToModelIfBindingResultError(bindingResultPerson, model);
                return "createUserPage";
            }
        }

        if (!userService.addUser(user, person)) {
            model.addAttribute("usernameError", "User " + user.getUsername() +" exists.");
        }
        return "wallpaperPage";
    }

    @GetMapping("/pickOperatUser/{people}")
    public String pickOperatUser(@PathVariable People people, Model model) {

        model.addAttribute("pickedPeople", people);
        return "pickOperatUserPage";
    }

    @GetMapping("/deleteUserOrNot")
    public String deleteUserOrNot(@ModelAttribute("pickedPeople") People people) {

        return "deleteUserOrNotPage";
    }

    @GetMapping("/changeUser")
    public String changeUser(@ModelAttribute("pickedPeople") People people) {

        return "changeUserPage";
    }

    @PostMapping("/changeUser")
    public String changeUser(@Valid Person person, BindingResult bindingResult, Model model,
                             @ModelAttribute("pickedPeople") People people) {

        if(bindingResult.hasErrors()) {
            ControllerUtils.addErrorToModelIfBindingResultError(bindingResult, model);

            return "changeUserPage";
        }

        people.setFirstName(person.getFirstName());
        people.setLastName(person.getLastName());
        peopleRepo.save(people);

        return "wallpaperPage";
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    @PostMapping("/deleteTheUser")
    public String deleteTheUser(@ModelAttribute("pickedPeople") People people) {

        peopleRepo.delete(people);
        User user = people.getUser();
        userRepo.delete(user);

        return "wallpaperPage";
    }
}
