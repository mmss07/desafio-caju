package com.caju.autorizador.controller;

import com.caju.autorizador.dto.ClientDTO;
import com.caju.autorizador.service.ClientService;
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
@RequestMapping("admin/v1/clients")
@Tag(name = "Clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @Operation(description = "Retorna a lista de Clientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta Realizada com sucesso!")
    })
    @GetMapping
    public ResponseEntity<List<ClientDTO>> listAllClient(){
        return clientService.listAllClients();
    }

}
