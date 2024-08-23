package com.caju.autorizador.dominio;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    double balanceFood;
    double balanceMeal;
    double balanceCash;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonManagedReference
    private Client client;

}
