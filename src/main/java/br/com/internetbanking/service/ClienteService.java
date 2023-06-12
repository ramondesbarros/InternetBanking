package br.com.internetbanking.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.internetbanking.dto.ClienteDTO;
import br.com.internetbanking.dto.DepositoBancario;
import br.com.internetbanking.dto.HistoricoTransacaoDTO;
import br.com.internetbanking.dto.SaqueConta;

public interface ClienteService {

	void cadastrarCliente(ClienteDTO cliente);

	void depositarUmValor(DepositoBancario depositoBancario, String numeroDaConta);

	List<ClienteDTO> retornarTodosClientesCadastrados();

	BigDecimal sacarValor(SaqueConta saqueConta, String numeroDaConta);
	
	List<HistoricoTransacaoDTO> consultarHistoricoTransacoesMovimentacaoPorData(
			BigDecimal valorDeSaque, 
			String numeroDaConta,
			Date dataInicio,
			Date dataFim);
}
