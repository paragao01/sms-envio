package br.com.unipix.envio.mongo.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import br.com.unipix.envio.enumeration.StatusSmsEnum;
import br.com.unipix.envio.mongo.model.CampanhaDocument;
import br.com.unipix.envio.mongo.repository.CampanhaDocumentRepositoryManual;

public class CampanhaDocumentRepositoryManualImpl implements CampanhaDocumentRepositoryManual{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Override
	public void updateStatusSms(LocalDateTime data, StatusSmsEnum status) {
		Query query = new Query(new Criteria("dataAgendada").is(data));
		Update update = Update.update("status", status.getName());
		mongoTemplate.updateMulti(query, update, CampanhaDocument.class);
	}


	@Override
	public void updateStatusSms(List<CampanhaDocument> documentos, StatusSmsEnum status) {
		List<String> ids = documentos.stream().map(d -> d.getId()).collect(Collectors.toList()); 
		Query query = new Query(new Criteria("_id").in(ids));
		Update update = Update.update("status", status.getName());
		mongoTemplate.updateMulti(query, update, CampanhaDocument.class);
	}
}
