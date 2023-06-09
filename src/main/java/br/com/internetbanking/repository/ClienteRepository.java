package br.com.internetbanking.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.internetbanking.dto.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Modifying
	@Query(value = "UPDATE cliente c SET c.saldo = :saldo WHERE c.numero_da_conta = :conta", nativeQuery = true)
	void atualizarValorDoSaldo(@Param("saldo") BigDecimal saldo, @Param("conta") String numeroDaConta);
	
	@Query(value = "SELECT c.saldo FROM cliente c WHERE c.numero_da_conta = :conta", nativeQuery = true)
	BigDecimal consultarSaldo(@Param("conta") String numeroDaConta);
	
	@Query(value = "SELECT c.plano_exclusive FROM cliente c WHERE c.numero_da_conta = :conta", nativeQuery = true)
	Boolean consultarPlanExclusive(@Param("conta") String numeroDaConta);

}
