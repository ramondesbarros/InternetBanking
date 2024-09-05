package com.internetbanking.request;

import java.util.Date;

public class HistoricoTransacaoRequest {

    public HistoricoTransacaoRequest(Long id, Date dataHistorico) {
        this.id = id;
        this.dataHistorico = dataHistorico;
    }

    private Long id;

    private Date dataHistorico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHistorico() {
        return dataHistorico;
    }

    public void setDataHistorico(Date dataHistorico) {
        this.dataHistorico = dataHistorico;
    }
}
