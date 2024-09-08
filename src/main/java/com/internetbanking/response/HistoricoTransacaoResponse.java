package com.internetbanking.response;

import com.internetbanking.enums.TipoMovimentacaoBancaria;

import java.math.BigDecimal;
import java.util.Date;

public class HistoricoTransacaoResponse {

    public HistoricoTransacaoResponse(Date dataTransacao, String tipoMovimentacaoBancaria, BigDecimal valor) {
        this.dataTransacao = dataTransacao;
        this.tipoMovimentacaoBancaria = tipoMovimentacaoBancaria;
        this.valor = valor;
    }

    private Date dataTransacao;

    private String tipoMovimentacaoBancaria;

    private BigDecimal valor;

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public String getTipoMovimentacaoBancaria() {
        return tipoMovimentacaoBancaria;
    }

    public void setTipoMovimentacaoBancaria(String tipoMovimentacaoBancaria) {
        this.tipoMovimentacaoBancaria = tipoMovimentacaoBancaria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
