package com.caju.autorizador.service;

import com.caju.autorizador.dominio.Account;
import com.caju.autorizador.dominio.Client;
import com.caju.autorizador.dto.ClientDTO;
import com.caju.autorizador.respository.IClientRepository;
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

class ClientServiceTest {

    @InjectMocks
    ClientService clientService;

    @Mock
    IClientRepository iClientRepository;


    @Mock
    MappingService mappingService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarClientes(){
        var clientDTO1 = new ClientDTO();
        clientDTO1.setMcc("5411");
        clientDTO1.setClientId(1);
        clientDTO1.setName("Caju");
        clientDTO1.setEmail("caju@caju.com.br");
        var client = new Client();
        client.setMcc("5411");
        client.setClientId(1);
        client.setName("Caju");
        client.setEmail("caju@caju.com.br");
        client.setAccount(new Account());
        var listClient = List.of(client);

        when(mappingService.convertToDto(client)).thenReturn(clientDTO1);
        when(iClientRepository.findAll()).thenReturn(listClient);

        ResponseEntity<List<ClientDTO>> responseEntity = clientService.listAllClients();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }



}