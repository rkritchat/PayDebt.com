package com.gravity.paydebt;

import com.google.gson.Gson;
import com.gravity.paydebt.model.DebtDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PaydebtApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class DebtIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private Gson gson = new Gson();

    @Test
    public void createDebt() throws Exception{
        DebtDetail debtDetail = new DebtDetail();
        debtDetail.setDebtorId("rkritchat")
                .setCreditorId("somchai")
                .setAmount(500.00)
                .setStatus(0)
                .setDetail("ค่าขนม");

        mockMvc.perform(MockMvcRequestBuilders.post("/debt/create")
                .content(gson.toJson(debtDetail))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Create Debt Successfully"));
    }
}
