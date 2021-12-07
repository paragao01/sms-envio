package br.com.unipix.envio.mongo.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.com.unipix.envio.enumeration.StatusSmsEnum;

public interface CampanhaDocumentRepositoryManual {

	void updateStatusSms(LocalDateTime data, StatusSmsEnum status, Long idCampanhaSql);
	
	Long updateStatusSms(List<String> ids, Long idCampanhaSql, StatusSmsEnum status);

}
