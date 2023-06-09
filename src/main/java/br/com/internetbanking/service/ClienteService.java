package br.com.internetbanking.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.internetbanking.dto.Cliente;
import br.com.internetbanking.dto.HistoricoTransacaoDTO;

public interface ClienteService {

	void cadastrarCliente(Cliente cliente);

	void depositarUmValor(BigDecimal valorDeposito, String numeroDaConta);

	List<Cliente> retornarTodosClientesCadastrados();

	BigDecimal sacarValor(BigDecimal valorDeSaque, String numeroDaConta);
	
	List<HistoricoTransacaoDTO> consultarHistoricoTransacoesMovimentacaoPorData(
			BigDecimal valorDeSaque, 
			String numeroDaConta,
			Date dataInicio,
			Date dataFim);
}
