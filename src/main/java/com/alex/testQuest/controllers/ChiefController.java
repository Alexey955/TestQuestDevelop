package com.alex.testQuest.controllers;

import com.alex.testQuest.dto.Person;
import com.alex.testQuest.entities.Department;
import com.alex.testQuest.entities.People;
import com.alex.testQuest.entities.User;
import com.alex.testQuest.repos.PeopleRepo;
import com.alex.testQuest.roles.Roles;
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
    private PeopleRepo peopleRepo;

    @GetMapping("/chiefMain")
    public String chiefMain() {
        return "chiefMainPage";
    }


    @GetMapping("/showChiefUserList")
    public String showUserList(@AuthenticationPrincipal User user, Model model) {

        List<Person> personList = new ArrayList<>();

        People people = user.getPeople();
        Department department = people.getDepartment();
        List<People> employeeList = peopleRepo.findAllByDepartment(department);

        for(People employee: employeeList){
            if(!employee.getUser().getRoles().toString().equals("[" + Roles.CHIEF.toString() + "]")){
                Person person = new Person(employee.getId(), employee.getFirstName(), employee.getLastName());
                person.setDepartmentName(department.getDepartmentName());
                person.setRole(Roles.EMPLOYEE.toString());

                personList.add(person);
            }
        }

        model.addAttribute("personList", personList);

        return "chiefUserListPage";
    }
}
