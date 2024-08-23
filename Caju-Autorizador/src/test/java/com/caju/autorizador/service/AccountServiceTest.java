package com.caju.autorizador.service;

import com.caju.autorizador.dominio.Account;
import com.caju.autorizador.dominio.Client;
import com.caju.autorizador.dto.AccountDTO;
import com.caju.autorizador.respository.IAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.when;

class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    IAccountRepository iAccountRepository;

    @Mock
    MappingService mappingService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarContas(){

        var accountDTO = AccountDTO.builder().accountId(1).balanceCash(100).balanceFood(200).balanceMeal(300).build();
        var client = new Client();
        client.setMcc("5411");
        client.setClientId(1);
        client.setName("Caju");
        client.setEmail("caju@caju.com.br");
        var account = new Account();
        account.setClient(client);
        account.setAccountId(1);
        account.setBalanceCash(100);
        var listClient = List.of(account);

        when(mappingService.convertToDto(account)).thenReturn(accountDTO);
        when(iAccountRepository.findAll()).thenReturn(listClient);

        ResponseEntity<List<AccountDTO>> responseEntity = accountService.listAllAccounts();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}