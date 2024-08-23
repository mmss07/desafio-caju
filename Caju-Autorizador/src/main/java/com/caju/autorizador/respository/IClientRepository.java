package com.caju.autorizador.respository;

import com.caju.autorizador.dominio.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Integer> {
}
