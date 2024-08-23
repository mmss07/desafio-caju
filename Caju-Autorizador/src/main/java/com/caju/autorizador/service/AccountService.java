package com.caju.autorizador.service;

import com.caju.autorizador.dominio.Account;
import com.caju.autorizador.dto.AccountDTO;
import com.caju.autorizador.respository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {


    private IAccountRepository iAccountRepository;

    private MappingService mappingService;

    @Autowired
    public AccountService(IAccountRepository iAccountRepository, MappingService mappingService) {
        this.iAccountRepository = iAccountRepository;
        this.mappingService = mappingService;
    }

    public ResponseEntity<List<AccountDTO>> listAllAccounts(){
        List<Account> accountRecords = iAccountRepository.findAll();
        List<AccountDTO> listAccountDTO = new ArrayList<>();
        for (Account accountRecord : accountRecords) {
            AccountDTO accountDTO = mappingService.convertToDto(accountRecord);
            listAccountDTO.add(accountDTO);
        }
        return ResponseEntity.ok(listAccountDTO);
    }


}
