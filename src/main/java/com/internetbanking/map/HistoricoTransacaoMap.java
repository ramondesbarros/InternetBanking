package com.internetbanking.map;

import com.internetbanking.dto.ClienteDTO;
import com.internetbanking.dto.HistoricoTransacaoDTO;
import com.internetbanking.enums.TipoMovimentacaoBancaria;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class HistoricoTransacaoMap {

    public HistoricoTransacaoDTO criarHistoricoTransacao(ClienteDTO clienteDTO, TipoMovimentacaoBancaria tipoMovimentacaoBancaria, BigDecimal valor) {



        return new HistoricoTransacaoDTO(tipoMovimentacaoBancaria,valor.toString(), new Date(), clienteDTO);
    }
}
