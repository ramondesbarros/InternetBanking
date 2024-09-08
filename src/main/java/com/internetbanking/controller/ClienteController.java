package com.internetbanking.controller;

import com.internetbanking.dto.ClienteDTO;
import com.internetbanking.dto.HistoricoTransacaoDTO;
import com.internetbanking.request.ClienteRequest;
import com.internetbanking.request.DepositoContaCorrenteRequest;
import com.internetbanking.request.HistoricoTransacaoRequest;
import com.internetbanking.request.SacarValorResquest;
import com.internetbanking.response.ClienteResponse;
import com.internetbanking.response.HistoricoTransacaoResponse;
import com.internetbanking.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private static Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @RequestMapping("/cadastrar")
    @PostMapping
    public ResponseEntity<String> cadastrarCliente(@RequestBody ClienteRequest request) {

        clienteService.cadastrarCliente(request);

        return new ResponseEntity<>("Cliente cadastrado", HttpStatus.CREATED);
    }

    @RequestMapping("/listaclientes")
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> retornarTodosClientesCadastrados() {

        List<ClienteResponse> listaTodosClientes = clienteService.retornarTodosClientesCadastrados();

        return ResponseEntity.ok(listaTodosClientes);
    }

    @RequestMapping("/sacarValor/{id}")
    @PutMapping
    public ResponseEntity<String> sacarValor(@RequestBody SacarValorResquest valorSaque, @PathVariable("id") Long id) throws RuntimeException, IllegalArgumentException {

        try {

            clienteService.sacarValor(valorSaque, id);
            return new ResponseEntity<>("Saque efetuado com sucesso!", HttpStatus.CREATED);
        } catch (IllegalArgumentException i) {

            LOGGER.info("Valor do saque e taxa a cima so valor do saldo em conta corrente!\"");
            return new ResponseEntity<>("Valor de saque e taxa a cima do valor de saldo de conta corrente", HttpStatus.BAD_REQUEST);

        } catch (RuntimeException e) {

            LOGGER.info("Valor do Saque maior que o do saldo bancario!");
        }

        return new ResponseEntity<>("Valor do Saque maior que o do saldo bancario!", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/depositarValor/{id}")
    @PostMapping
    ResponseEntity<String> depositarValor(@RequestBody DepositoContaCorrenteRequest valorDeposito, @PathVariable("id") Long id) {

        clienteService.depositarValor(valorDeposito, id);
        return new ResponseEntity<>("Dposito efetuado com sucesso!", HttpStatus.CREATED);
    }

    //TODO
    @GetMapping("/consultarHistorico/{id}")
    public ResponseEntity<List<HistoricoTransacaoResponse>> consultarHistoricoTransacoesMovimentacaoData(@RequestBody HistoricoTransacaoRequest historicoTransacaoRequest, @PathVariable("id") Long id) {


        List<HistoricoTransacaoResponse> listaHistoricoTrnsacao = clienteService.consultarHistoricoTransacoesMovimentacaoData(historicoTransacaoRequest, id);

        return ResponseEntity.ok(listaHistoricoTrnsacao);

    }
}
