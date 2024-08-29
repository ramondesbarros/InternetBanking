package com.internetbanking.dto;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cliente")
public class ClienteDTO {

    public ClienteDTO () {
        super();
    }

    public ClienteDTO(Long id, String nome, Boolean planoExclusive, BigDecimal saldo, Date dataNascimento, Set<HistoricoTransacaoDTO> historicoTransacoes) {
        this.id = id;
        this.nome = nome;
        this.planoExclusive = planoExclusive;
        this.saldo = saldo;
        this.dataNascimento = dataNascimento;
        this.historicoTransacoes = historicoTransacoes;
    }

    public ClienteDTO(String nome, Boolean planoExclusive, BigDecimal saldo, Date dataNascimento) {

        this.nome = nome;
        this.planoExclusive = planoExclusive;
        this.saldo = saldo;
        this.dataNascimento = dataNascimento;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "plano_exclusive")
    private Boolean planoExclusive;

    @Column(name = "saldo", precision = 10, scale = 2)
    private BigDecimal saldo;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @OneToMany(mappedBy="cliente")
    private Set<HistoricoTransacaoDTO> historicoTransacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Set<HistoricoTransacaoDTO> getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    public void setHistoricoTransacoes(Set<HistoricoTransacaoDTO> historicoTransacoes) {
        this.historicoTransacoes = historicoTransacoes;
    }
}
