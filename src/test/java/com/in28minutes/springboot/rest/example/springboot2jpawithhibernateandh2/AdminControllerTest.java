package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers.AdminController;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Department;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Employee;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Person;
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
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/tableInfo-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/tableInfo-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("Alexandr")
public class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Model model;

    @MockBean
    private BindingResult bindingResult;

    @Test
    public void showUserList(){
        User user = new User();
        user.setId((long) 3);
        user.setUsername("Alexandr");

        Assert.assertEquals(adminController.showUserList(model), "userListPage");
    }

    @Test
    @Transactional
    public void addUser(){
        User user = new User();
        user.setId((long) 5);
        user.setUsername("usernameOne");
        user.setPassword("passwordOne");
        user.setRoles(Collections.singleton(Roles.EMPLOYEE));

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setDepartmentName("departmentName");
        person.setRole("Employee");

        Assert.assertEquals(adminController.addUser(user, bindingResult, person, bindingResult, model /*"Employee", "departmentName", "firstName", "lastName"*/), "wallpaperPage");
    }

    @Test
    public void changeEmployee(){
        User user = new User();
        user.setId((long) 10);
        user.setRoles(Collections.singleton(Roles.EMPLOYEE));

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");

        Assert.assertEquals(adminController.changeUser(person, bindingResult, model, user), "wallpaperPage");
    }

    @Test
    public void changeChief(){
        User user = new User();
        user.setId((long) 20);
        user.setRoles(Collections.singleton(Roles.CHIEF));

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");

        Assert.assertEquals(adminController.changeUser(person, bindingResult, model, user), "wallpaperPage");
    }

    @Test
    public void deleteTheUser(){
        User user = new User();
        user.setId((long) 10);
        user.setRoles(Collections.singleton(Roles.EMPLOYEE));

        Assert.assertEquals(adminController.deleteTheUser(user), "wallpaperPage");
    }

    @Test
    public void showUserListByAdmin() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/showUserList").requestAttr("model", model))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.xpath("/html/body/div").nodeCount(3));
    }

    @Test
    public void trueEmployeeRegistrationByAdmin() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/registrationByAdmin")
                .param("username", "Username")
                .param("password", "Password")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("departmentName", "departmentName")
                .param("role", "Employee")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print());

        this.mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("Username").password("Password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Employee employeeTest = new Employee((long) 2, "firstName", "lastName");
        Department departmentTest = new Department((long) 2, "departmentName");
        employeeTest.setDepartment(departmentTest);

        Assert.assertEquals(employeeTest, employeeRepo.findFirstById((long) 2));
    }

    @Test
    public void changeUserByAdmin() throws Exception {

        User userOld = new User();
        userOld.setId((long) 10);
        userOld.setRoles(Collections.singleton(Roles.EMPLOYEE));

        MockHttpServletRequestBuilder multipart = multipart("/changeUser").sessionAttr("pickedUser", userOld)
                .param("firstName", "newFirstName")
                .param("lastName", "newLastName")
                .with(csrf());

        this.mockMvc.perform(multipart);

        Employee employeeTest = new Employee((long) 10, "newFirstName", "newLastName");
        Department departmentTest = new Department((long) 10, "DepartmentOne");
        employeeTest.setDepartment(departmentTest);

        Assert.assertEquals(employeeTest, employeeRepo.findFirstById((long) 10));
    }

    @Test
    public void deleteTheUserByAdmin() throws Exception {

        User user = new User();
        user.setId((long) 10);
        user.setRoles(Collections.singleton(Roles.EMPLOYEE));

        MockHttpServletRequestBuilder multipart = multipart("/deleteTheUser").sessionAttr("pickedUser", user)
                .with(csrf());

        this.mockMvc.perform(multipart);

        Employee employeeTest = new Employee((long) 10, "newFirstName", "newLastName");
        Department departmentTest = new Department((long) 10, "DepartmentOne");
        employeeTest.setDepartment(departmentTest);

        Assert.assertNull(employeeRepo.findFirstById((long) 10));
    }

}
