package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.*;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.ChiefRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.DepartmentRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.EmployeeRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.UserRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.roles.Roles;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
    public String addUser(User user, Model model, @RequestParam String radioDel,
                          @RequestParam String departmentName, @RequestParam String firstName,
                          @RequestParam String lastName) {

        if (!userService.addUser(user, radioDel, firstName, lastName, departmentName)) {
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
    public String changeUser(@RequestParam String firstName, @RequestParam String lastName,
                             @ModelAttribute("pickedUser") User userOld) {

        switch (userOld.getRoles().iterator().next().toString()){
            case "EMPLOYEE":
                Employee employee = employeeRepo.findFirstById(userOld.getId());
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employeeRepo.save(employee);
                break;

            case "CHIEF":
                Chief chief = chiefRepo.findFirstById(userOld.getId());
                chief.setFirstName(firstName);
                chief.setLastName(lastName);
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
