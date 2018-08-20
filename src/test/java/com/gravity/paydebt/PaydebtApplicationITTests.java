package com.gravity.paydebt;

import com.google.gson.Gson;
import com.gravity.paydebt.model.UserInfo;
import com.gravity.paydebt.model.UserPwd;
import com.gravity.paydebt.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    @Autowired
    UserService userRepository;

    private Gson gson = new Gson();

    @Before
    public void preparedData() {
        UserInfo userInfo = mockBean("test");
        userRepository.createUser(userInfo);
    }

    @Test
    public void register() throws Exception{
        UserInfo userInfo = mockBean("rkritchat1");

        mockMvc.perform(post("/user/register")
                .content(gson.toJson(userInfo))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void updateUserDetail() throws Exception{
        UserInfo userInfo = mockBean("test").setEmail("test@gmail.com");

        mockMvc.perform(patch("/user/update-info")
                .content(gson.toJson(userInfo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(userInfo.setUserPwd(null))))
                .andReturn();
    }

    @Test
    public void updateUserPwd() throws Exception{
        UserPwd userPwd = new UserPwd("test", "111", "ROLE_USER");
        mockMvc.perform(patch("/user/update-pwd")
                .content(gson.toJson(userPwd))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(userPwd)));
    }


    private UserInfo mockBean(String id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(id)
                .setAddress("Knightbridge")
                .setEmail("kritchat.r@gmail.com")
                .setFirstName("Kritchat")
                .setLastName("Rojanaphruk")
                .setTell("055090323")
                .setUserPwd(new UserPwd(id, "1234", "ROLE_USER"));
        return userInfo;
    }


}
