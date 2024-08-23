package com.caju.autorizador.service;

import com.caju.autorizador.dominio.Account;
import com.caju.autorizador.dominio.Client;
import com.caju.autorizador.dto.RespostaDTO;
import com.caju.autorizador.dto.TransactionDTO;
import com.caju.autorizador.respository.IAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(PowerMockRunner.class)
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @Mock
    private IAccountRepository iAccountRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testDebitAccount1_FOOD_Success() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccount(1);
        transactionDTO.setMcc("5411");
        transactionDTO.setTotalAmount(90);
        Account account = new Account();
        account.setBalanceFood(100.0);

        Mockito.when(iAccountRepository.findById(transactionDTO.getAccount())).thenReturn(Optional.of(account));

        ResponseEntity<RespostaDTO> response = transactionService.debitAccount1(transactionDTO);

        assertNotNull(response.getBody());
        assertEquals("00", ((RespostaDTO)response.getBody()).getCode());

        Mockito.verify(iAccountRepository).save(account);
    }

    @Test
    void testDebitAccount1_MEAL_Success() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccount(1);
        transactionDTO.setMcc("5811");
        transactionDTO.setTotalAmount(90);
        Account account = new Account();
        account.setBalanceMeal(100.0);

        Mockito.when(iAccountRepository.findById(transactionDTO.getAccount())).thenReturn(Optional.of(account));

        ResponseEntity<RespostaDTO> response = transactionService.debitAccount1(transactionDTO);

        assertNotNull(response);
        assertEquals("00", ((RespostaDTO)response.getBody()).getCode());
    }

    @Test
    void testDebitAccount1_AccountNotFound() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccount(1);
        transactionDTO.setMcc("5421");
        transactionDTO.setTotalAmount(1000);
        Mockito.when(iAccountRepository.findById(transactionDTO.getAccount())).thenReturn(Optional.empty());

        ResponseEntity<RespostaDTO> response = transactionService.debitAccount1(transactionDTO);

        assertNotNull(response);
        assertEquals("07", ((RespostaDTO)response.getBody()).getCode());
    }

    @Test
    void testDebitAccount2_FOOD_Success() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccount(1);
        transactionDTO.setMcc("5411");
        transactionDTO.setTotalAmount(90);
        Account account = new Account();
        account.setBalanceFood(100.0);

        Mockito.when(iAccountRepository.findById(transactionDTO.getAccount())).thenReturn(Optional.of(account));

        ResponseEntity<RespostaDTO> response = transactionService.debitAccount2(transactionDTO);

        assertNotNull(response.getBody());
        assertEquals("00", ((RespostaDTO)response.getBody()).getCode());

        Mockito.verify(iAccountRepository).save(account);
    }

    @Test
    void testDebitAccount2_MEAL_Success() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccount(1);
        transactionDTO.setMcc("5811");
        transactionDTO.setTotalAmount(90);
        Account account = new Account();
        account.setBalanceMeal(100.0);

        Mockito.when(iAccountRepository.findById(transactionDTO.getAccount())).thenReturn(Optional.of(account));

        ResponseEntity<RespostaDTO> response = transactionService.debitAccount2(transactionDTO);

        assertNotNull(response);
        assertEquals("00", ((RespostaDTO)response.getBody()).getCode());
    }

    @Test
    void testDebitAccount2_CASH_Success() {
        var transactionDTO = new TransactionDTO();
        transactionDTO.setAccount(1);
        transactionDTO.setMcc("5411");
        transactionDTO.setTotalAmount(90);
        var account = new Account();
        account.setBalanceCash(100.0);

        Mockito.when(iAccountRepository.findById(transactionDTO.getAccount())).thenReturn(Optional.of(account));

        ResponseEntity<RespostaDTO> response = transactionService.debitAccount2(transactionDTO);

        assertNotNull(response);
        assertEquals("00", ((RespostaDTO)response.getBody()).getCode());
    }

    @Test
    void testDebitAccount3_FOOD_Success() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccount(0);
        transactionDTO.setMcc("");
        transactionDTO.setMerchant("Caju");
        transactionDTO.setTotalAmount(90);
        Account account = new Account();
        account.setBalanceCash(100.0);
        var client = new Client();
        client.setMcc("5411");
        account.setClient(client);
        Mockito.when(iAccountRepository.findByClientByName("Caju")).thenReturn(Optional.of(account));
        ResponseEntity<RespostaDTO> response = transactionService.debitAccount3(transactionDTO);

        assertNotNull(response);
        assertEquals("00", ((RespostaDTO)response.getBody()).getCode());
    }


}