package com.alex.testQuest.controllers;

import com.alex.testQuest.dto.Person;
import com.alex.testQuest.repos.DepartmentRepo;
import com.alex.testQuest.entities.Chief;
import com.alex.testQuest.entities.Employee;
import com.alex.testQuest.entities.User;
import com.alex.testQuest.repos.ChiefRepo;
import com.alex.testQuest.repos.EmployeeRepo;
import com.alex.testQuest.repos.UserRepo;
import com.alex.testQuest.roles.Roles;
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
@SessionAttributes("pickedUser")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private ChiefRepo chiefRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/adminMain")
    public String adminMain() {
        return "adminMainPage";
    }

    @GetMapping("/showUserList")
    public String showUserList(Model model) {
        List<Person> personList = new ArrayList<>();

        List<Chief> chiefList = chiefRepo.findAll();
        for(Chief chief: chiefList){
            Person person = new Person(chief.getId(), chief.getFirstName(), chief.getLastName());
            String departmentName = departmentRepo.findDepartmentNameById(chief.getDepartment().getId());
            person.setDepartmentName(departmentName);
            person.setRole(Roles.CHIEF.toString());

            personList.add(person);
        }

        List<Employee> employeeList = employeeRepo.findAll();
        for(Employee employee: employeeList){
            Person person = new Person(employee.getId(), employee.getFirstName(), employee.getLastName());
            String departmentName = departmentRepo.findDepartmentNameById(employee.getDepartment().getId());
            person.setDepartmentName(departmentName);
            person.setRole(Roles.EMPLOYEE.toString());

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

    @GetMapping("/pickOperatUser/{user}")
    public String pickOperatUser(@PathVariable User user, Model model) {

        model.addAttribute("pickedUser", user);
        return "pickOperatUserPage";
    }

    @GetMapping("/deleteUserOrNot")
    public String deleteUserOrNot(@ModelAttribute("pickedUser") User user) {

        return "deleteUserOrNotPage";
    }

    @GetMapping("/changeUser")
    public String changeUser(@ModelAttribute("pickedUser") User user) {

        return "changeUserPage";
    }

    @PostMapping("/changeUser")
    public String changeUser(@Valid Person person, BindingResult bindingResult, Model model,
                             @ModelAttribute("pickedUser") User userOld) {

        if(bindingResult.hasErrors()) {
            ControllerUtils.addErrorToModelIfBindingResultError(bindingResult, model);

            return "changeUserPage";
        }

        switch (userOld.getRoles().iterator().next().toString()){
            case "EMPLOYEE":
                Employee employee = employeeRepo.findFirstById(userOld.getId());
                employee.setFirstName(person.getFirstName());
                employee.setLastName(person.getLastName());
                employeeRepo.save(employee);
                break;

            case "CHIEF":
                Chief chief = chiefRepo.findFirstById(userOld.getId());
                chief.setFirstName(person.getFirstName());
                chief.setLastName(person.getLastName());
                chiefRepo.save(chief);
                break;
        }
        return "wallpaperPage";
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    @PostMapping("/deleteTheUser")
    public String deleteTheUser(@ModelAttribute("pickedUser") User user) {

        switch (user.getRoles().iterator().next().toString()){
            case "EMPLOYEE": employeeRepo.deleteById(user.getId()); break;
            case "CHIEF": chiefRepo.deleteById(user.getId()); break;
        }

        userRepo.deleteById(user.getId());

        return "wallpaperPage";
    }
}
