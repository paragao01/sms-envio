package br.com.unipix.envio.mongo.repository;

import java.time.LocalDateTime;

import br.com.unipix.envio.enumeration.StatusCampanhaEnum;
import br.com.unipix.envio.enumeration.StatusProcessoEnum;


public interface CampanhaDashboardRepositoryManual {
	
	void updateStatusAgendado(LocalDateTime data, StatusProcessoEnum status, String id);
	
	void updateStatusCampanha(String id, StatusCampanhaEnum status);

}
