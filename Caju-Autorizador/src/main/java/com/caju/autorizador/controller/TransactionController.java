package com.caju.autorizador.controller;

import com.caju.autorizador.dto.RespostaDTO;
import com.caju.autorizador.dto.TransactionDTO;
import com.caju.autorizador.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("admin/v1/autorizadores")
@Tag(name = "Authorizer")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(description = "L1. Autorizador simples.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação processada!")
    })
    @PostMapping(value = "/l1", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaDTO> debitAccountL1(@RequestBody TransactionDTO transactionDTO){
        return transactionService.debitAccount1(transactionDTO);
    }

    @Operation(description = "L2. Autorizador com fallback")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação processada!")
    })
    @PostMapping(value = "/l2")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaDTO> debitAccountL2(@RequestBody TransactionDTO transactionDTO){
        return transactionService.debitAccount2(transactionDTO);
    }

    @Operation(description = "L3. Dependente do nome do comerciante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação processada!")
    })
    @PostMapping(value = "/l3")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RespostaDTO> debitAccountL3(@RequestBody TransactionDTO transactionDTO){
        return transactionService.debitAccount3(transactionDTO);
    }

}
