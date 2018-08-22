package com.gravity.paydebt;

import com.google.gson.Gson;
import com.gravity.paydebt.model.DebtDetail;
import com.gravity.paydebt.model.PaymentDetail;
import com.gravity.paydebt.model.TransactionDetail;
import com.gravity.paydebt.repository.debt.DebtRepository;
import com.gravity.paydebt.repository.payment.TransactionRepository;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PaydebtApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class PaymentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DebtRepository debtRepository;

    private long debtReference = 0;

    private Gson gson = new Gson();

    @Before
    public void createDebt() {
        debtRepository.save(
                new DebtDetail().setDebtorReference("kritchat")
                        .setCreditorReference("somchai")
                        .setAmount(500.00)
                        .setStatus(0)
                        .setDescription("ค่าขนม"));
        debtReference = debtRepository.findByDebtorReference("kritchat").get(0).getId();
    }


    @Test
    public void pay() throws Exception{
        PaymentDetail paymentDetail = mockBean();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/payment/pay")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(paymentDetail))

        ).andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(MockMvcResultMatchers.content().string("Payment Done."));
    }

    private PaymentDetail mockBean() {
       return new PaymentDetail()
                .setUsername("rkritchat")
                .setDebtRef(debtReference)
                .setAmount(500.00)
                .setAmountPaid(500.00)
                .setDescription("For Food")
                .setStatus(0);
    }

}
