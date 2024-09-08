package com.internetbanking.repository;

import com.internetbanking.dto.HistoricoTransacaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HistoricoTransacaoRepository extends JpaRepository<HistoricoTransacaoDTO, Long> {

    @Query(value = "SELECT data_transacao, tipo_movimentacao_bancaria, valor FROM historico_transacao WHERE data_transacao = :data AND cliente_id = :cliente_id", nativeQuery = true)
    List<Object[]> consultaHistorico(@Param("data") Date date, @Param("cliente_id") Long id);
}
