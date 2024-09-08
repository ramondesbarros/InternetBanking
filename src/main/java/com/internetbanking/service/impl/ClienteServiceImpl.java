package com.internetbanking.service.impl;

import com.internetbanking.dto.ClienteDTO;
import com.internetbanking.dto.HistoricoTransacaoDTO;
import com.internetbanking.enums.TipoMovimentacaoBancaria;
import com.internetbanking.map.ClienteMap;
import com.internetbanking.map.HistoricoTransacaoMap;
import com.internetbanking.math.CalculadoraTaxa;

import com.internetbanking.repository.HistoricoTransacaoRepository;
import com.internetbanking.request.ClienteRequest;
import com.internetbanking.request.DepositoContaCorrenteRequest;
import com.internetbanking.request.HistoricoTransacaoRequest;
import com.internetbanking.request.SacarValorResquest;
import com.internetbanking.response.ClienteResponse;
import com.internetbanking.response.HistoricoTransacaoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.internetbanking.repository.ClienteRepository;
import com.internetbanking.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HistoricoTransacaoRepository historicoTransacaoRepository;

    @Autowired
    private CalculadoraTaxa calculadoraTaxa;

    @Autowired
    private ClienteMap map;

    @Autowired
    private HistoricoTransacaoMap historicoTransacaoMap;


    @Override
    public void cadastrarCliente(ClienteRequest request) {

        clienteRepository.save(map.requestToDTO(request));
    }

    @Override
    public List<ClienteResponse> retornarTodosClientesCadastrados() {

        List<ClienteDTO> listaCliente = clienteRepository.findAll();

        List<ClienteResponse> listaClientesResponse = listaCliente.stream().map(c -> {

            return new ClienteResponse(c.getNome(), c.getPlanoExclusive(), c.getSaldo());
        }).collect(Collectors.toList());

        return listaClientesResponse;

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

            criarHistoricoDeTransacao(id, TipoMovimentacaoBancaria.SAQUE, new BigDecimal(valorSaque.getValorSaque()));

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

        criarHistoricoDeTransacao(id, TipoMovimentacaoBancaria.DEPOSITO, new BigDecimal(valorDeposito.getValorDeposito()));

    }

    private void criarHistoricoDeTransacao(Long id, TipoMovimentacaoBancaria tipoMovimentacaoBancaria, BigDecimal valorDeposito)  {

            historicoTransacaoRepository
                    .save(historicoTransacaoMap
                    .criarHistoricoTransacao(clienteRepository.getReferenceById(id),
                            tipoMovimentacaoBancaria,
            new BigDecimal(valorDeposito.toString())));
    }

    @Override
    public List<HistoricoTransacaoResponse> consultarHistoricoTransacoesMovimentacaoData(HistoricoTransacaoRequest historicoTransacaoRequest, Long id) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dataHistorico = historicoTransacaoRequest.getDataHistorico();


        Date date;
        try {
            date = sdf.parse(dataHistorico);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        List<Object[]>  historicosTransacao = historicoTransacaoRepository.consultaHistorico(date, id);

        System.out.println(historicosTransacao);

        List<HistoricoTransacaoResponse> transacoes = historicosTransacao.stream().map(t -> {

            return new HistoricoTransacaoResponse((Date) t[0], (String) t[1].toString(), new BigDecimal(t[2].toString()));
        }).collect(Collectors.toList());

        return transacoes;
    }

    private void verificarValorSaqueDeposito() {

    }

    private BigDecimal consultarValorSaldo(Long id) {

        return clienteRepository.consultarValorSaldo(id);

    }
}
