package br.com.internetbanking.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.internetbanking.enums.MovimentacaoBancaria;

@Entity
@Table(name = "historico_transacao")
public class HistoricoTransacaoDTO {
	
	
	
	public HistoricoTransacaoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoricoTransacaoDTO(Date date, MovimentacaoBancaria movimentacaoBancaria, String numeroDaConta) {
		this.date = date;
		this.movimentacaoBancaria = movimentacaoBancaria;
		this.numeroDaConta = numeroDaConta;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "data_da_transacao")
	private Date date;

	@Column(name = "tipo_da_movimentacao")
	private MovimentacaoBancaria movimentacaoBancaria;

	@Column(name = "numero_da_conta")
	private String numeroDaConta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MovimentacaoBancaria getMovimentacaoBancaria() {
		return movimentacaoBancaria;
	}

	public void setMovimentacaoBancaria(MovimentacaoBancaria movimentacaoBancaria) {
		this.movimentacaoBancaria = movimentacaoBancaria;
	}

	public String getNumeroDaConta() {
		return numeroDaConta;
	}

	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}
}
