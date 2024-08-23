package com.caju.autorizador.dto;

import lombok.*;

import java.io.Serializable;

@Data
public class ClientDTO implements Serializable {

    private int clientId;
    private String name;
    private String email;
    private String mcc;

}
