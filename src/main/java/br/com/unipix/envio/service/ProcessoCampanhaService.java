package br.com.unipix.envio.service;

import br.com.unipix.envio.model.ProcessoCampanha;


public interface ProcessoCampanhaService {
	ProcessoCampanha obterUltimoProcesso();
	void enviarCampanhaAgendada(ProcessoCampanha processo);
	void enviarCampanha();
}
