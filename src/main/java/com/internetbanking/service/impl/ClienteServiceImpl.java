package com.internetbanking.service.impl;

import com.internetbanking.dto.ClienteDTO;
import com.internetbanking.map.ClienteMap;
import com.internetbanking.math.CalculadoraTaxa;

import com.internetbanking.request.ClienteRequest;
import com.internetbanking.request.DepositoContaCorrenteRequest;
import com.internetbanking.request.SacarValorResquest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.internetbanking.repository.ClienteRepository;
import com.internetbanking.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CalculadoraTaxa calculadoraTaxa;

    @Autowired
    private ClienteMap map;


    @Override
    public void cadastrarCliente(ClienteRequest request) {

        clienteRepository.save(map.requestToDTO(request));
    }

    @Override
    public List<ClienteDTO> retornarTodosClientesCadastrados() {

        List<ClienteDTO> listaCliente = clienteRepository.findAll();

        return listaCliente;

    }

    @Override
    public void sacarValor(SacarValorResquest valorSaque, Long id) throws RuntimeException {

        BigDecimal valorSaldoBancario = consultarValorSaldo(id);
        BigDecimal valorDeSaque = new BigDecimal(valorSaque.getValorSaque());

        if (valorDeSaque.intValue() > valorSaldoBancario.intValue()) {

            throw new RuntimeException();
        }

        Boolean planoExclusive = clienteRepository.consultarPlanoExclusive(id);

        try {

            BigDecimal valordoSaldoAtualizar = calculadoraTaxa
                    .calcularValorDoSaldoContandoTaxaAdmin(valorSaldoBancario, valorDeSaque, planoExclusive);

            clienteRepository.atualizarValorSaldo(valordoSaldoAtualizar, id);

        } catch (IllegalArgumentException e) {

            throw new IllegalArgumentException();
        }
    }

    @Override
    public void depositarValor(DepositoContaCorrenteRequest valorDeposito, Long id) {

        BigDecimal saldoAtual = clienteRepository.consultarValorSaldo(id);

        BigDecimal valorDepositoString = new BigDecimal(valorDeposito.getValorDeposito());

        BigDecimal saldo = new BigDecimal(saldoAtual.toString()).add(valorDepositoString);

        clienteRepository.atualizarValorSaldo(saldo, id);
    }

    @Override
    public void consultarHistoricoTransacoesMovimentacaoData() {

    }

    private void verificarValorSaqueDeposito() {

    }

    private BigDecimal consultarValorSaldo(Long id) {

        return clienteRepository.consultarValorSaldo(id);

    }
}
