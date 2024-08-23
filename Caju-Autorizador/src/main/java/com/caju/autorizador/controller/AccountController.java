package com.caju.autorizador.controller;

import com.caju.autorizador.dto.AccountDTO;
import com.caju.autorizador.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("admin/v1/accounts")
@Tag(name = "Accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @Operation(description = "Retorna a lista de Contas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta Realizada com sucesso!")
    })
    @GetMapping
    public ResponseEntity<List<AccountDTO>> listAllAccount(){
        return accountService.listAllAccounts();
    }

}
