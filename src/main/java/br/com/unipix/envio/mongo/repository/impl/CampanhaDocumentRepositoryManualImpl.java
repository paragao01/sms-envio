package br.com.unipix.envio.mongo.repository.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import br.com.unipix.envio.enumeration.StatusSmsEnum;
import br.com.unipix.envio.mongo.model.CampanhaDocument;
import br.com.unipix.envio.mongo.repository.CampanhaDocumentRepositoryManual;

@Repository
public class CampanhaDocumentRepositoryManualImpl implements CampanhaDocumentRepositoryManual{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Override
	public void updateStatusSms(LocalDateTime data, StatusSmsEnum status, Long idCampanhaSql) {
		Query query = new Query(new Criteria("dataAgendada").is(data).and("idCampanhaSql").is(idCampanhaSql));
		Update update = Update.update("status", status.getName());
		mongoTemplate.updateMulti(query, update, CampanhaDocument.class);
	}


	@Override
	public Long updateStatusSms(List<String> ids, Long idCampanhaSql, StatusSmsEnum status) {
		Query query = new Query(new Criteria("id").in(ids).and("idCampanhaSql").is(idCampanhaSql));
		Update update = Update.update("status", status.getName());
		UpdateResult result = mongoTemplate.updateMulti(query, update, CampanhaDocument.class);
		return result.getModifiedCount();
	}
}
