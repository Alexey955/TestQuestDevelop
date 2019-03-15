package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.controllers.MainController;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.domains.User;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.roles.Roles;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MainController mainController;

    @Test
    public void pickMainPageEmployee() {
        User user = new User();
        user.setId((long) 1);
        user.setUsername("Username");
        user.setPassword("Password");
        user.setRoles(Collections.singleton(Roles.EMPLOYEE));

        Assert.assertEquals(mainController.pickMainPage(user), "employeeMainPage");
    }

    @Test
    public void pickMainPageChief() {
        User user = new User();
        user.setId((long) 1);
        user.setUsername("Username");
        user.setPassword("Password");
        user.setRoles(Collections.singleton(Roles.CHIEF));

        Assert.assertEquals(mainController.pickMainPage(user), "chiefMainPage");
    }

    @Test
    public void pickMainPageAdmin() {
        User user = new User();
        user.setId((long) 1);
        user.setUsername("Username");
        user.setPassword("Password");
        user.setRoles(Collections.singleton(Roles.ADMIN));

        Assert.assertEquals(mainController.pickMainPage(user), "adminMainPage");
    }
}
