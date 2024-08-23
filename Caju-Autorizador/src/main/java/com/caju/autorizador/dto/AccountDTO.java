package com.caju.autorizador.dto;


import lombok.*;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private int accountId;
    private int clientId;
    double balanceFood;
    double balanceMeal;
    double balanceCash;


}
