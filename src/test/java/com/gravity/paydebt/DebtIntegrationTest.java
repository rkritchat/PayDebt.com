package com.gravity.paydebt;

import com.google.gson.Gson;
import com.gravity.paydebt.model.DebtDetail;
import com.gravity.paydebt.repository.debt.DebtRepository;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Autowired
    private DebtRepository debtRepository;

    @Before
    public void initData() {
        debtRepository.save(mockBean("TEST1", "TEST2"));
    }

    @Test
    public void createDebt() throws Exception{
        DebtDetail debtDetail = mockBean("rkritchat", "somchay");

        mockMvc.perform(MockMvcRequestBuilders.post("/debt/create")
                .content(gson.toJson(debtDetail))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Create Debt Successfully"));
    }

    @Test
    public void getDebtDetailByDebtor() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                .post("/debt/detail")
                .content(gson.toJson(new DebtDetail().setDebtorReference("TEST1")))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].creditorReference").value("TEST2"));
    }


    private DebtDetail mockBean(String debtorId, String creditorId) {
        return new DebtDetail().setDebtorReference(debtorId)
                .setCreditorReference(creditorId)
                .setAmount(500.00)
                .setStatus(0)
                .setDescription("ค่าขนม");
    }

}
