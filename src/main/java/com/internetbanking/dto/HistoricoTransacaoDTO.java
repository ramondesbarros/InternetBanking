package com.internetbanking.dto;

import com.internetbanking.enums.TipoMovimentacaoBancaria;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "historico_transacao")
public class HistoricoTransacaoDTO {

    public HistoricoTransacaoDTO(Long id, TipoMovimentacaoBancaria tipoMovimentacaoBancaria, String valor, Date dataTransacao, ClienteDTO cliente) {
        this.id = id;
        this.tipoMovimentacaoBancaria = tipoMovimentacaoBancaria;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
        this.cliente = cliente;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_movimentacao_bancaria")
    private TipoMovimentacaoBancaria tipoMovimentacaoBancaria;

    @Column(name = "valor")
    private String valor;

    @Column(name = "data_transacao")
    private Date dataTransacao;

    @ManyToOne
    @JoinColumn(name="cliente_id", nullable=false)
    private ClienteDTO cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMovimentacaoBancaria getTipoMovimentacaoBancaria() {
        return tipoMovimentacaoBancaria;
    }

    public void setTipoMovimentacaoBancaria(TipoMovimentacaoBancaria tipoMovimentacaoBancaria) {
        this.tipoMovimentacaoBancaria = tipoMovimentacaoBancaria;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}
