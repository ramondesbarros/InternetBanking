package com.internetbanking.service;

import com.internetbanking.dto.ClienteDTO;
import com.internetbanking.repository.ClienteRepository;
import com.internetbanking.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    private List<ClienteDTO> clientesResponse;


    @BeforeEach
    public void setUp() {

        clientesResponse = new ArrayList<>();

        ClienteDTO cd1 = new ClienteDTO();

        cd1.setNome("Ramon Barros");
        cd1.setPlanoExclusive(true);
        cd1.setSaldo(new BigDecimal("0"));
        cd1.setDataNascimento(new Date());

        ClienteDTO cd2 = new ClienteDTO();

        cd2.setNome("Jackie Stewart");
        cd2.setPlanoExclusive(false);
        cd2.setSaldo(new BigDecimal("0"));
        cd2.setDataNascimento(new Date());

        ClienteDTO cd3 = new ClienteDTO();

        cd3.setNome("Adrian Newey");
        cd3.setPlanoExclusive(true);
        cd3.setSaldo(new BigDecimal("0"));
        cd3.setDataNascimento(new Date());

        clientesResponse.add(cd1);
        clientesResponse.add(cd2);
        clientesResponse.add(cd3);

    }

    @DisplayName("Deve retornar lista de clientes")
    @Test
    public void deveRetornarListaDeCliente() {

        when(clienteRepository.findAll()).thenReturn(clientesResponse);

        List<ClienteDTO> clientesResponseTest = clienteRepository.findAll();

        Assertions.assertEquals(3, clientesResponseTest.size());

    }

    @DisplayName("Deve retornar saldo do cliente")
    @Test
    public void deveRetornarSaldoConta(){

        when(clienteRepository.consultarValorSaldo(1L)).thenReturn(new BigDecimal("100"));

        BigDecimal saldoConta = clienteRepository.consultarValorSaldo(1L);

        Assertions.assertEquals(new BigDecimal(100), saldoConta);
    }

}
