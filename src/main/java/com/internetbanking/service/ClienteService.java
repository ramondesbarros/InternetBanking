package com.internetbanking.service;

import com.internetbanking.dto.ClienteDTO;
import com.internetbanking.request.ClienteRequest;
import com.internetbanking.request.DepositoContaCorrenteRequest;
import com.internetbanking.request.SacarValorResquest;

import java.math.BigDecimal;
import java.util.List;

public interface ClienteService {

    void cadastrarCliente(ClienteRequest request);

    List<ClienteDTO> retornarTodosClientesCadastrados();

    void sacarValor(SacarValorResquest valorSaque, Long id);

    void depositarValor(DepositoContaCorrenteRequest valorDeposito, Long id);

    void consultarHistoricoTransacoesMovimentacaoData();

}
