package br.com.internetbanking.service;

import java.util.Date;
import java.util.List;

import br.com.internetbanking.dto.HistoricoTransacaoDTO;

public interface HistoricoTransacaoService {

	void atualizarHistoricoTransacao(HistoricoTransacaoDTO historicoTransacao);
	
	List<HistoricoTransacaoDTO> consultarHistoricoTransacao(String numeroDaConta, Date dataInicio, Date dataFim);
}
