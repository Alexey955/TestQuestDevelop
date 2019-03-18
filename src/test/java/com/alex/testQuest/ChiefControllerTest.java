package com.alex.testQuest;

import com.alex.testQuest.controllers.ChiefController;
import com.alex.testQuest.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/tableInfo-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/tableInfo-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ChiefControllerTest {

    @Autowired
    private ChiefController chiefController;

    @MockBean
    private Model model;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("Valery")
    public void showUserList(){
        User user = new User();
        user.setId((long) 20);
        user.setUsername("Valery");

        Assert.assertEquals(chiefController.showUserList(user, model), "chiefUserListPage");
    }

    @Test
    @WithUserDetails("Valery")
    public void showUserListOne() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/showChiefUserList").requestAttr("model", model))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.xpath("/html/body/div").nodeCount(1));
    }
}
