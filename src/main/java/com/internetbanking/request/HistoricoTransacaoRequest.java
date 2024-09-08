package com.internetbanking.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.stereotype.Component;

@Component
public class HistoricoTransacaoRequest {

    @Temporal(TemporalType.DATE)
    private String dataHistorico;


    public String getDataHistorico() {
        return dataHistorico;
    }

    public void setDataHistorico(String dataHistorico) {
        this.dataHistorico = dataHistorico;
    }
}
