package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Department;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.Employee;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.DepartmentRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.EmployeeRepo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.repos.UserRepo;
import org.hibernate.dialect.H2Dialect;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RegistrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Test
    public void trueEmployeeRegistrationTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/registration")
                .param("username", "Username")
                .param("password", "Password")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("departmentName", "departmentOne")
                .param("radioDel", "Employee")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(redirectedUrl("/login"));

        this.mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("Username").password("Password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Employee employeeTest = new Employee((long) 1, "firstName", "lastName");
        Department departmentTest = new Department((long) 1, "departmentOne");
        employeeTest.setDepartment(departmentTest);

        Assert.assertTrue(employeeTest.equals(employeeRepo.findById((long) 1)));
    }

}
