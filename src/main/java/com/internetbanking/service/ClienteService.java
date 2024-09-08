package com.internetbanking.service;

import com.internetbanking.dto.ClienteDTO;
import com.internetbanking.dto.HistoricoTransacaoDTO;
import com.internetbanking.request.ClienteRequest;
import com.internetbanking.request.DepositoContaCorrenteRequest;
import com.internetbanking.request.HistoricoTransacaoRequest;
import com.internetbanking.request.SacarValorResquest;
import com.internetbanking.response.ClienteResponse;
import com.internetbanking.response.HistoricoTransacaoResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

public interface ClienteService {

    void cadastrarCliente(ClienteRequest request);

    List<ClienteResponse> retornarTodosClientesCadastrados();

    void sacarValor(SacarValorResquest valorSaque, Long id);

    void depositarValor(DepositoContaCorrenteRequest valorDeposito, Long id);

    List<HistoricoTransacaoResponse> consultarHistoricoTransacoesMovimentacaoData(HistoricoTransacaoRequest historicoTransacaoRequest, Long id);

}
