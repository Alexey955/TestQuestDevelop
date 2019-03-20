package com.alex.testQuest;

import com.alex.testQuest.dto.Person;
import com.alex.testQuest.controllers.RegistrationController;
import com.alex.testQuest.entities.Department;
import com.alex.testQuest.entities.Employee;
import com.alex.testQuest.entities.User;
import com.alex.testQuest.repos.EmployeeRepo;
import com.alex.testQuest.roles.Roles;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"/tableInfo-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RegistrationController registrationController;

    @MockBean
    private Model model;

    @MockBean
    private BindingResult bindingResult;

    @Test
    @Transactional
    public void addEmployee(){
        User user = new User();
        user.setUsername("usernameOne");
        user.setPassword("passwordOne");
        user.setRoles(Collections.singleton(Roles.EMPLOYEE));

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setDepartmentName("departmentName");
        person.setRole("Employee");

        Assert.assertEquals(registrationController.addUser(user, bindingResult, person, bindingResult, model /*"Employee", "departmentName", "firstName", "lastName"*/), "redirect:/login");
    }

    @Test
    public void addChief(){
        User user = new User();
        user.setUsername("usernameTwo");
        user.setPassword("passwordTwo");
        user.setRoles(Collections.singleton(Roles.CHIEF));

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setDepartmentName("departmentName");
        person.setRole("Chief");

        Assert.assertEquals(registrationController.addUser(user, bindingResult, person, bindingResult, model /*"Chief", "departmentName", "firstName", "lastName"*/), "redirect:/login");
    }

    @Test
    public void addAdmin(){
        User user = new User();
        user.setUsername("usernameThree");
        user.setPassword("passwordThree");
        user.setRoles(Collections.singleton(Roles.ADMIN));

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setDepartmentName("departmentName");
        person.setRole("Employee");

        Assert.assertEquals(registrationController.addUser(user, bindingResult, person, bindingResult, model /*"Admin", "departmentName", "firstName", "lastName"*/), "redirect:/login");
    }

//    @Test
//    public void trueEmployeeRegistrationTest() throws Exception {
//        MockHttpServletRequestBuilder multipart = multipart("/registration")
//                .param("username", "Username")
//                .param("password", "Password")
//                .param("firstName", "firstName")
//                .param("lastName", "lastName")
//                .param("departmentName", "departmentName")
//                .param("role", "Employee")
//                .with(csrf());
//
//        this.mockMvc.perform(multipart)
//                .andDo(print())
//                .andExpect(redirectedUrl("/login"));
//
//        this.mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("Username").password("Password"))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//
//        Employee employeeTest = new Employee((long) 4, "firstName", "lastName");
//        Department departmentTest = new Department((long) 4, "departmentName");
//        employeeTest.setDepartment(departmentTest);
//
//        Assert.assertEquals(employeeTest, employeeRepo.findFirstById((long) 4));
//    }

}
