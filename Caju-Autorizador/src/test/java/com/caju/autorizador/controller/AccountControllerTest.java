package com.caju.autorizador.controller;

import com.caju.autorizador.dto.AccountDTO;
import com.caju.autorizador.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.powermock.api.mockito.PowerMockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    AccountController clientController;

    @Mock
    AccountService clientService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

    }

    @Test
    void listAllAccounts() throws Exception {
        var list = List.of(new AccountDTO());
        ResponseEntity<List<AccountDTO>> ok = ResponseEntity.ok(list);

        when(clientService.listAllAccounts()).thenReturn(ok);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get( "/admin/v1/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();


    }

}