package com.internetbanking.repository;

import com.internetbanking.dto.HistoricoTransacaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface HistoricoTransacaoRepository extends JpaRepository<HistoricoTransacaoDTO, Long> {

    @Query(value = "SELECT * FROM t.valor, t.data_transacao, t.tipo_movimentacao_bancaria FROM historico_transacao WHERE data_transacao = :data AND cliente_id :id;", nativeQuery = true)
    List<HistoricoTransacaoDTO> consultaHistorico(@Param("data") Date dataMovimentacao, @Param("id") Long id);
}
