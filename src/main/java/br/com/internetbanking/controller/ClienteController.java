package br.com.internetbanking.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.internetbanking.dto.ClienteDTO;
import br.com.internetbanking.dto.DepositoBancario;
import br.com.internetbanking.dto.HistoricoTransacaoDTO;
import br.com.internetbanking.dto.SaqueConta;
import br.com.internetbanking.service.ClienteService;

@RequestMapping(value = "/cliente")
@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/cadastrarCliente")
	@PostMapping
	void cadastrarCliente(@RequestBody ClienteDTO cliente) {
		
		clienteService.cadastrarCliente(cliente);
	}

	@PatchMapping(value = "/depositarUmValor/{numeroDaConta}")
	void depositarUmValor(@RequestBody DepositoBancario depositoBancario, @PathVariable("numeroDaConta") String numeroDaConta) {

		clienteService.depositarUmValor(depositoBancario, numeroDaConta);
	}

	@RequestMapping(value = "/retornarTodosClientesCadastrados")
	@GetMapping
	List<ClienteDTO> retornarTodosClientesCadastrados() {
		
		return clienteService.retornarTodosClientesCadastrados();
	}

	@RequestMapping(value = "/sacarValor/{numeroDaConta}")
	@GetMapping
	BigDecimal sacarValor(@RequestBody SaqueConta saqueConta, @PathVariable("numeroDaConta") String numeroDaConta) {
		
		return clienteService.sacarValor(saqueConta, numeroDaConta);
		
	}
	
	@RequestMapping(value = "/consultarHistoricoTransacoesMovimentacaoPorData/{numeroDaConta}")
	@GetMapping
	List<HistoricoTransacaoDTO> consultarHistoricoTransacoesMovimentacaoPorData(@RequestParam(value = "dataInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio, @RequestParam(value = "dataFim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim, @PathVariable("numeroDaConta") String numeroDaConta) throws ParseException {
				
		return clienteService.consultarHistoricoTransacoesMovimentacaoPorData(numeroDaConta, dataInicio, dataFim);
		
	}

}
