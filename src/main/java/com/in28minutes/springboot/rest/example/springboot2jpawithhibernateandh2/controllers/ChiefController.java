package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.*;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.ChiefRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.DepartmentRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.EmployeeRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.UserRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('CHIEF')")
public class ChiefController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ChiefRepo chiefRepo;

    @GetMapping("/chiefMain")
    public String chiefMain() {
        return "chiefMainPage";
    }


    @GetMapping("/showChiefUserList")
    public String showUserList(@AuthenticationPrincipal User user, Model model) {

        Department departmentChief = chiefRepo.findDepartmentById(user.getId());
        List<Person> personList = new ArrayList<>();

        List<Employee> employeeList = employeeRepo.findAll();
        for(Employee employee: employeeList){
            if(departmentChief.getId().equals(employee.getDepartment().getId())){
                Person person = new Person(employee.getId(), employee.getFirstName(), employee.getLastName());
                String departmentName = departmentChief.getDepartmentName();
                person.setDepartmentName(departmentName);
                person.setRole(Roles.EMPLOYEE.toString());

                personList.add(person);
            }
        }
        model.addAttribute("personList", personList);

        return "chiefUserListPage";
    }
}
