package com.internetbanking.repository;

import com.internetbanking.dto.ClienteDTO;
//import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDTO, Long> {

    @Query(value = "SELECT c.saldo FROM cliente c WHERE c.id = :id", nativeQuery = true)
    BigDecimal consultarValorSaldo(@Param("id") Long id);

    @Query(value = "SELECT c.plano_exclusive FROM cliente c WHERE c.id = :id", nativeQuery = true)
    Boolean consultarPlanoExclusive(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE cliente c SET c.saldo = ?1 WHERE c.id = ?2", nativeQuery = true)
    BigDecimal atualizarValorSaldo(BigDecimal saldo, Long id);

}
