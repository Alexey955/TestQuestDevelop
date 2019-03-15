package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers.RegistrationController;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Department;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Employee;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.User;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.EmployeeRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.roles.Roles;
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

    @Test
    @Transactional
    public void addEmployee(){
        User user = new User();
        user.setUsername("usernameOne");
        user.setPassword("passwordOne");
        user.setRoles(Collections.singleton(Roles.EMPLOYEE));

        Assert.assertEquals(registrationController.addUser(user, model, "Employee", "departmentName", "firstName", "lastName"), "redirect:/login");
    }

    @Test
    public void addChief(){
        User user = new User();
        user.setUsername("usernameTwo");
        user.setPassword("passwordTwo");
        user.setRoles(Collections.singleton(Roles.CHIEF));

        Assert.assertEquals(registrationController.addUser(user, model, "Chief", "departmentName", "firstName", "lastName"), "redirect:/login");
    }

    @Test
    public void addAdmin(){
        User user = new User();
        user.setUsername("usernameThree");
        user.setPassword("passwordThree");
        user.setRoles(Collections.singleton(Roles.ADMIN));

        Assert.assertEquals(registrationController.addUser(user, model, "Admin", "departmentName", "firstName", "lastName"), "redirect:/login");
    }

    @Test
    public void trueEmployeeRegistrationTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/registration")
                .param("username", "Username")
                .param("password", "Password")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("departmentName", "departmentName")
                .param("radioDel", "Employee")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(redirectedUrl("/login"));

        this.mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("Username").password("Password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Employee employeeTest = new Employee((long) 4, "firstName", "lastName");
        Department departmentTest = new Department((long) 4, "departmentName");
        employeeTest.setDepartment(departmentTest);

        Assert.assertEquals(employeeTest, employeeRepo.findFirstById((long) 4));
    }

}
