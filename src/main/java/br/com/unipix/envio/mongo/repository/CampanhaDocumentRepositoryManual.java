package br.com.unipix.envio.mongo.repository;

import java.time.LocalDateTime;

import br.com.unipix.envio.enumeration.StatusSmsEnum;

public interface CampanhaDocumentRepositoryManual {

	public void updateStatusSms(LocalDateTime data, StatusSmsEnum status);
	
	public void updateStatusSms(Long idCampanhaSql, StatusSmsEnum status);

}
