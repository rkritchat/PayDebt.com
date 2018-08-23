package com.gravity.paydebt;

import com.google.gson.Gson;
import com.gravity.paydebt.model.DebtDetail;
import com.gravity.paydebt.model.PaymentDetail;
import com.gravity.paydebt.model.TransactionDetail;
import com.gravity.paydebt.repository.debt.DebtRepository;
import com.gravity.paydebt.repository.payment.PaymentRepository;
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

    @Autowired
    private PaymentRepository paymentRepository;

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

    @Before
    public void createPayment() {
        PaymentDetail paymentDetail = mockBean("kritchat",2L,100).setTransactionRef(5L);
        paymentRepository.save(paymentDetail);
    }

    @Test
    public void pay() throws Exception{
        PaymentDetail paymentDetail = mockBean("kritchat",debtReference,200);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/payment/pay")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(paymentDetail))

        ).andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(MockMvcResultMatchers.content().string("Payment Done."));
    }

    @Test
    public void getPaymentFromDebtorId() throws Exception{
        DebtDetail debtDetail = new DebtDetail().setDebtorReference("kritchat").setStatus(0);
        mockMvc.perform(MockMvcRequestBuilders.post("/payment/detail")
                .content(gson.toJson(debtDetail))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("kritchat"));
    }

    @Test
    public void confirmPayment() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .post("/payment/confirm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(mockBean("kritchat",debtReference,500).setStatus(1)))
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    private PaymentDetail mockBean(String username, Long debtReference, double amtPaid) {
       return new PaymentDetail()
                .setUsername(username)
                .setDebtRef(debtReference)
                .setAmount(500.00)
                .setAmountPaid(amtPaid)
                .setDescription("For Food")
                .setStatus(0);
    }

}
