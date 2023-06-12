package br.com.internetbanking.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.internetbanking.dto.ClienteDTO;
import br.com.internetbanking.dto.DepositoBancario;
import br.com.internetbanking.dto.HistoricoTransacaoDTO;
import br.com.internetbanking.dto.SaqueConta;
import br.com.internetbanking.repository.ClienteRepository;
import br.com.internetbanking.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private static final String TAXA_ZERO_VIRGULA_QUATRO_POR_CENTO = "0.4";
	private static final String TAXA_UM_POR_CENTO = "1";

	@Autowired
	private ClienteRepository clienteRepository;

	public void cadastrarCliente(ClienteDTO cliente) {

		clienteRepository.save(cliente);
	}

	public List<ClienteDTO> retornarTodosClientesCadastrados() {

		return clienteRepository.findAll();
	}

	public BigDecimal sacarValor(SaqueConta saqueConta, String numeroDaConta) {

		BigDecimal saldoAtual = clienteRepository.consultarSaldo(numeroDaConta);
		BigDecimal valorDeSaque = new BigDecimal(saqueConta.getValorDeSaque());

		if (valorDeSaque.intValue() <= 0 || saldoAtual.intValue() == 0
				|| valorDeSaque.intValue() > saldoAtual.intValue()) {

			throw new IllegalArgumentException();

		}

		Boolean planoExclusive = clienteRepository.consultarPlanExclusive(numeroDaConta);

		if (planoExclusive.booleanValue() == true || valorDeSaque.intValue() <= 100) {

			subtrairValorNoSaldoDaConta(valorDeSaque, numeroDaConta);

			return valorDeSaque;

		}

		if (valorDeSaque.intValue() <= saldoAtual.intValue()) {

			if (valorDeSaque.intValue() > 100 && valorDeSaque.intValue() <= 300) {

				BigDecimal valorDeSaldoComSaque = saldoAtual.subtract(valorDeSaque);
				BigDecimal taxaDeJuros = saldoAtual.multiply(new BigDecimal(TAXA_ZERO_VIRGULA_QUATRO_POR_CENTO));
				BigDecimal saldoAtualizado = valorDeSaldoComSaque.subtract(taxaDeJuros);

				clienteRepository.atualizarValorDoSaldo(saldoAtualizado, numeroDaConta);

				return valorDeSaque;

			}

			if (valorDeSaque.intValue() > 300) {

				BigDecimal valorDeSaldoComSaque = saldoAtual.subtract(valorDeSaque);
				BigDecimal taxaDeJuros = saldoAtual.multiply(new BigDecimal(TAXA_UM_POR_CENTO));
				BigDecimal saldoAtualizado = valorDeSaldoComSaque.subtract(taxaDeJuros);

				clienteRepository.atualizarValorDoSaldo(saldoAtualizado, numeroDaConta);

			}

		} else {

			throw new IllegalArgumentException();
		}

		return valorDeSaque;
	}

	public void depositarUmValor(DepositoBancario depositoBancario, String numeroDaConta) {

		BigDecimal valorDeposito = new BigDecimal(depositoBancario.getValorDoDeposito());

		if (valorDeposito.intValue() < 0) {

			throw new IllegalArgumentException();

		} else {

			ClienteDTO cliente = clienteRepository.findByNumeroDaConta(numeroDaConta);

			BigDecimal saldoAtualizado = cliente.getSaldo().add(valorDeposito);

			cliente.setSaldo(saldoAtualizado);

			clienteRepository.save(cliente);
		}

	}

	@Override
	public List<HistoricoTransacaoDTO> consultarHistoricoTransacoesMovimentacaoPorData(BigDecimal valorDeSaque,
			String numeroDaConta, Date dataInicio, Date dataFim) {
		// TODO Auto-generated method stub
		return null;
	}

	private void subtrairValorNoSaldoDaConta(BigDecimal valorDeSaldoAtualizado, String numeroDaConta) {
		BigDecimal saldoAtual = clienteRepository.consultarSaldo(numeroDaConta);

		if (saldoAtual.intValue() < valorDeSaldoAtualizado.intValue()) {

			throw new IllegalArgumentException();
		}

		clienteRepository.atualizarValorDoSaldo(valorDeSaldoAtualizado, numeroDaConta);
	}

}
