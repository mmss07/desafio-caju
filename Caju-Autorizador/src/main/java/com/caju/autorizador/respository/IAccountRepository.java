package com.caju.autorizador.respository;

import com.caju.autorizador.dominio.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer > {

    Optional<Account> findById(int accountId);

    @Query("SELECT ac FROM Account ac WHERE ac.client.name = :name ")
    Optional<Account> findByClientByName(@Param(value = "name") String name);
}
