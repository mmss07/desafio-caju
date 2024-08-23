package com.caju.autorizador.service;

import com.caju.autorizador.dominio.Account;
import com.caju.autorizador.dominio.Client;
import com.caju.autorizador.dto.AccountDTO;
import com.caju.autorizador.dto.ClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingService {

    private ModelMapper modelMapper;

    @Autowired
    public MappingService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountDTO convertToDto(Account account) {
        return modelMapper.map(account, AccountDTO.class);
    }

    public ClientDTO convertToDto(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }


}
