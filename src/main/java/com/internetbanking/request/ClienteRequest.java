package com.internetbanking.request;

import com.internetbanking.dto.HistoricoTransacaoDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class ClienteRequest {

    public ClienteRequest(String nome, Boolean planoExclusive, String dataNascimento) {
        this.nome = nome;
        this.planoExclusive = planoExclusive;
        this.dataNascimento = dataNascimento;
    }

    private String nome;

    private Boolean planoExclusive;

    private String dataNascimento;

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

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}
