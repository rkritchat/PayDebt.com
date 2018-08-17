package com.gravity.paydebt;

import com.google.gson.Gson;
import com.gravity.paydebt.model.UserInfo;
import com.gravity.paydebt.model.UserPwd;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PaydebtApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class PaydebtApplicationITTests {

    @Autowired
    MockMvc mockMvc;

    private Gson gson = new Gson();

    @Test
    public void register() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("rkritchat22")
                .setAddress("Knightbridge")
                .setEmail("kritchat.r@gmail.com")
                .setFirstName("Kritchat")
                .setLastName("Rojanaphruk")
                .setTell("055090323")
                .setUserPwd(new UserPwd("rkritchat", "1234", "ROLE_USER"));

        MvcResult mvcResult = mockMvc.perform(post("/user/register")
                .content(gson.toJson(userInfo))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        System.out.println("REGISTER RESULT : " + mvcResult.getResponse().getContentAsString());
    }

}
