package com.caju.autorizador.service;

import com.caju.autorizador.dominio.Client;
import com.caju.autorizador.dto.ClientDTO;
import com.caju.autorizador.respository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private IClientRepository iClientRepository;

    private MappingService mappingService;

    @Autowired
    public ClientService(IClientRepository iClientRepository, MappingService mappingService) {
        this.iClientRepository = iClientRepository;
        this.mappingService = mappingService;
    }

    public ResponseEntity<List<ClientDTO>> listAllClients(){

        List<Client> clientRecords = iClientRepository.findAll();
        List<ClientDTO> listAccountDTO = new ArrayList<>();
        clientRecords.forEach(client -> {
            ClientDTO clientDTO = mappingService.convertToDto(client);
            listAccountDTO.add(clientDTO);
        });

        return ResponseEntity.ok(listAccountDTO);
    }




}
