package br.com.internetbanking.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.internetbanking.dto.Cliente;
import br.com.internetbanking.service.ClienteService;

@RequestMapping(value = "/cliente")
@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/cadastrarCliente")
	@PostMapping
	void cadastrarCliente(@RequestBody Cliente cliente) {
		
		clienteService.cadastrarCliente(cliente);
	}

	@RequestMapping(value = "/depositarUmValor")
	@PostMapping
	void depositarUmValor(BigDecimal valorDeposito, String numeroDaConta) {

		clienteService.depositarUmValor(valorDeposito, numeroDaConta);
	}

	@RequestMapping(value = "/retornarTodosClientesCadastrados")
	@GetMapping
	List<Cliente> retornarTodosClientesCadastrados() {
		
		return clienteService.retornarTodosClientesCadastrados();
	}

	@RequestMapping(value = "/sacarValor")
	@GetMapping
	BigDecimal sacarValor(BigDecimal valorDeSaque, String numeroDaConta) {
		
		return clienteService.sacarValor(valorDeSaque, numeroDaConta);
	}
}
