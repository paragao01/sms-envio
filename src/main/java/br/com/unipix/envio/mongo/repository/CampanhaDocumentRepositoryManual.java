package br.com.unipix.envio.mongo.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.com.unipix.envio.enumeration.StatusSmsEnum;
import br.com.unipix.envio.mongo.model.CampanhaDocument;

public interface CampanhaDocumentRepositoryManual {

	public void updateStatusSms(LocalDateTime data, StatusSmsEnum status);
	
	public void updateStatusSms(List<CampanhaDocument> documentos, StatusSmsEnum status);

}
