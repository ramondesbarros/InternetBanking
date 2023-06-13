package br.com.internetbanking.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.internetbanking.dto.HistoricoTransacaoDTO;
import br.com.internetbanking.repository.HistoricoTransacaoRepository;
import br.com.internetbanking.service.HistoricoTransacaoService;

@Service
public class HistoricoTransacaoServiceImpl implements HistoricoTransacaoService {

	@Autowired
	private HistoricoTransacaoRepository HistoricoTransacaoRepository;
	
	@Override
	public void atualizarHistoricoTransacao(HistoricoTransacaoDTO historicoTransacao) {

		HistoricoTransacaoRepository.save(historicoTransacao);
	}

	@Override
	public List<HistoricoTransacaoDTO> consultarHistoricoTransacao(String numeroDaConta, Date dataInicio, Date dataFim) {

		return HistoricoTransacaoRepository.consultarHistoricoDeTransacoes(numeroDaConta, dataInicio, dataFim);
	}

}
