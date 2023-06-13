package br.com.internetbanking.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.internetbanking.dto.HistoricoTransacaoDTO;

@Repository
public interface HistoricoTransacaoRepository extends JpaRepository<HistoricoTransacaoDTO, Long> {

	//MASTER WHERE LAST_UPDATED >= :startDate AND LAST_UPDATED <= :endDate
	@Query(value = "SELECT * FROM historico_transacao h WHERE h.numero_da_conta = :numero_da_conta AND h.data_da_transacao BETWEEN :dataInicio AND :dataFim", nativeQuery = true)
	List<HistoricoTransacaoDTO> consultarHistoricoDeTransacoes(@Param("numero_da_conta") String numeroDaConta, @Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
}
