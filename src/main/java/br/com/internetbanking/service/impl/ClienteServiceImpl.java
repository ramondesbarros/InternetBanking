package br.com.internetbanking.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.internetbanking.dto.Cliente;
import br.com.internetbanking.dto.HistoricoTransacaoDTO;
import br.com.internetbanking.repository.ClienteRepository;
import br.com.internetbanking.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public void cadastrarCliente(Cliente cliente) {

		clienteRepository.save(cliente);
	}

	public List<Cliente> retornarTodosClientesCadastrados() {

		return clienteRepository.findAll();
	}

	public BigDecimal sacarValor(BigDecimal valorDeSaque, String numeroDaConta) {

		// TODO: saque do valor da conta do cliente
		return null;
	}

	public void depositarUmValor(BigDecimal valorDeposito, String numeroDaConta) {

		if (valorDeposito.intValue() < 0) {

			throw new IllegalArgumentException();

		} else {

			BigDecimal saldoAtual = clienteRepository.consultarSaldo(numeroDaConta);
			BigDecimal valorDoDeposito = saldoAtual.add(valorDeposito);
			clienteRepository.atualizarValorDoSaldo(valorDoDeposito, numeroDaConta);
		}
	}

	@Override
	public List<HistoricoTransacaoDTO> consultarHistoricoTransacoesMovimentacaoPorData(BigDecimal valorDeSaque,
			String numeroDaConta, Date dataInicio, Date dataFim) {
		// TODO Auto-generated method stub
		return null;
	}
}
