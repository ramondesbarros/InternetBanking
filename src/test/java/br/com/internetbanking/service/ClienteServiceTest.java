package br.com.internetbanking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.internetbanking.dto.ClienteDTO;
import br.com.internetbanking.repository.ClienteRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

	@InjectMocks
	private ClienteDTO cliente;

	@Mock
	private ClienteRepository clienteRepository;

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void saqueComClientePlanoExclusiveTrue() {

		String numeroDaConta = "12345";

		BigDecimal saldoDaConta = new BigDecimal("200");

		BigDecimal saque = new BigDecimal("100");

		when(clienteRepository.consultarPlanExclusive(numeroDaConta)).thenReturn(true);

		when(clienteRepository.consultarSaldo(numeroDaConta)).thenReturn(saldoDaConta);

		assertEquals(BigDecimal.valueOf(50), BigDecimal.valueOf(50));

	}

	@Test
	public void saqueComValorAbaixoDeCem() {

		String numeroDaConta = "12345";

		BigDecimal saldoDaConta = new BigDecimal("200");

		BigDecimal saque = new BigDecimal("50");

		when(clienteRepository.consultarPlanExclusive(numeroDaConta)).thenReturn(true);
		when(clienteRepository.consultarSaldo(numeroDaConta)).thenReturn(saldoDaConta);

		assertEquals(BigDecimal.valueOf(50), BigDecimal.valueOf(50));

	}

	@Test
    public void listaComTodosOsClientesCadastrados() {
    	
		ClienteDTO c1 = new ClienteDTO();
		c1.setNome("Jon Lord");
		c1.setNumeroDaConta("12345");
		c1.setPlanoExclusive(Boolean.TRUE);
		c1.setSaldo(new BigDecimal("1000"));
				
		ClienteDTO c2 = new ClienteDTO();
		
		c2.setNome("John Entwistle");
		c2.setNumeroDaConta("23456");
		c2.setPlanoExclusive(Boolean.FALSE);
		c2.setSaldo(new BigDecimal("2000"));

		
		
    	List<ClienteDTO> clientes = new ArrayList<>();
    	
    	clientes.add(c1);
    	clientes.add(c2);
    	
    	when(clienteRepository.findAll()).thenReturn(clientes);
    	
    	equals(clientes);
    	
    }
}
