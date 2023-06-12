package br.com.internetbanking.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.internetbanking.dto.ClienteDTO;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDTO, Long> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE cliente c SET c.saldo = :saldo WHERE c.numero_da_conta = :conta", nativeQuery = true)
	void atualizarValorDoSaldo(@Param("saldo") BigDecimal saldo, @Param("conta") String numeroDaConta);
	
	@Query(value = "SELECT c.saldo FROM cliente c WHERE c.numero_da_conta = :conta", nativeQuery = true)
	BigDecimal consultarSaldo(@Param("conta") String numeroDaConta);
	
	@Query(value = "SELECT c.plano_exclusive FROM cliente c WHERE c.numero_da_conta = :conta", nativeQuery = true)
	Boolean consultarPlanExclusive(@Param("conta") String numeroDaConta);
	
	@Query(value = "SELECT * FROM cliente c WHERE c.numero_da_conta = :conta", nativeQuery = true)
	ClienteDTO encontrarClientesPeloNumeroDaConta(@Param("conta") String numeroDaConta);
	

	
	ClienteDTO findByNumeroDaConta(String numeroDaConta);

}
