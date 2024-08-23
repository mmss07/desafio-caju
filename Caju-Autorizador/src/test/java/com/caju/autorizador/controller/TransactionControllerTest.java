package com.caju.autorizador.controller;

import com.caju.autorizador.dto.RespostaDTO;
import com.caju.autorizador.dto.TransactionDTO;
import com.caju.autorizador.service.TransactionService;
import com.caju.autorizador.util.UtilJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.powermock.api.mockito.PowerMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    private final String ENDPOINT_BASE = "/admin/v1/autorizadores";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    void testDebitAccountL1_Success() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccount(1); // Set appropriate values
        transactionDTO.setMerchant("merchant1");
        transactionDTO.setTotalAmount(50.0);
        transactionDTO.setMcc("5411");

        RespostaDTO respostaDTO = new RespostaDTO("00");

        when(transactionService.debitAccount1(transactionDTO)).thenReturn(ResponseEntity.ok(respostaDTO));

        mockMvc.perform(post(ENDPOINT_BASE + "/l1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UtilJson.convertObjectToJson(transactionDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("00"));
    }

    @Test
    void testDebitAccountL2_Success() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccount(1); // Set appropriate values
        transactionDTO.setMerchant("merchant1");
        transactionDTO.setTotalAmount(50.0);
        transactionDTO.setMcc("5411");

        RespostaDTO respostaDTO = new RespostaDTO("00");

        when(transactionService.debitAccount2(transactionDTO)).thenReturn(ResponseEntity.ok(respostaDTO));

        mockMvc.perform(post(ENDPOINT_BASE + "/l2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UtilJson.convertObjectToJson(transactionDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("00"));
    }

    @Test
    void testDebitAccountL3_Success() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccount(1); // Set appropriate values
        transactionDTO.setMerchant("merchant1");
        transactionDTO.setTotalAmount(50.0);
        transactionDTO.setMcc("5411");

        RespostaDTO respostaDTO = new RespostaDTO("00");

        when(transactionService.debitAccount3(transactionDTO)).thenReturn(ResponseEntity.ok(respostaDTO));

        mockMvc.perform(post(ENDPOINT_BASE + "/l3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UtilJson.convertObjectToJson(transactionDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("00"));
    }


}