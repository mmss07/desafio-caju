package com.caju.autorizador.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int account;
    private double totalAmount;
    private String mcc;
    private String merchant;


}
