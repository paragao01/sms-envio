package br.com.unipix.envio.mongo.repository.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import br.com.unipix.envio.enumeration.StatusSmsEnum;
import br.com.unipix.envio.mongo.model.CampanhaDashboard;
import br.com.unipix.envio.mongo.repository.CampanhaDocumentRepositoryManual;

public class CampanhaDocumentRepositoryManualImpl implements CampanhaDocumentRepositoryManual{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Override
	public void updateStatusSms(LocalDateTime data, StatusSmsEnum status) {
		Query query = new Query(new Criteria("dataAgendada").is(data));
		Update update = Update.update("status", status.getName());
		mongoTemplate.updateMulti(query, update, CampanhaDashboard.class);
	}


	@Override
	public void updateStatusSms(Long idCampanhaSql, StatusSmsEnum status) {
		Query query = new Query(new Criteria("idCampanhaSql").is(idCampanhaSql));
		Update update = Update.update("status", status.getName());
		mongoTemplate.updateMulti(query, update, CampanhaDashboard.class);
	}
}
