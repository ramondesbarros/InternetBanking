package com.internetbanking.response;

import java.math.BigDecimal;
import java.util.Date;

public class ClienteResponse {

    public ClienteResponse(String nome, Boolean planoExclusive, BigDecimal saldo) {
        this.nome = nome;
        this.planoExclusive = planoExclusive;
        this.saldo = saldo;
    }

    private String nome;

    private Boolean planoExclusive;

    private BigDecimal saldo;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getPlanoExclusive() {
        return planoExclusive;
    }

    public void setPlanoExclusive(Boolean planoExclusive) {
        this.planoExclusive = planoExclusive;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}
