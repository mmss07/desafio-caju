package com.caju.autorizador.dominio;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;
    private String name;
    private String email;
    private String mcc;

    @OneToOne(mappedBy = "client")
    @JsonBackReference
    private Account account;

}
