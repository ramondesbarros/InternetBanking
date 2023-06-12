package br.com.internetbanking.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cliente")
public class ClienteDTO {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
	
    @Column(name = "nome", length = 40, nullable = false)
	private String nome;
	
    @Column(name = "plano_exclusive", nullable = false)
	private Boolean planoExclusive;
	
    @Column(name = "saldo", precision = 10, scale = 2, nullable = false)
	private BigDecimal saldo;
	
    @Column(name = "numero_da_conta", length = 10, unique = true, nullable = false)
	private String numeroDaConta;
	
    @Column(name = "data_de_nascimento", nullable = false)
	private Date dataDeNascimento;

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

	public String getNumeroDaConta() {
		return numeroDaConta;
	}

	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", planoExclusive=" + planoExclusive + ", saldo=" + saldo
				+ ", numeroDaConta=" + numeroDaConta + ", dataDeNascimento=" + dataDeNascimento + "]";
	}

}
