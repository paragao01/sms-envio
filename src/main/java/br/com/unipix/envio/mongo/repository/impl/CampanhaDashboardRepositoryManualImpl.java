package br.com.unipix.envio.mongo.repository.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import br.com.unipix.envio.enumeration.StatusCampanhaEnum;
import br.com.unipix.envio.enumeration.StatusProcessoEnum;
import br.com.unipix.envio.mongo.model.CampanhaDashboard;
import br.com.unipix.envio.mongo.repository.CampanhaDashboardRepositoryManual;

@Repository
public class CampanhaDashboardRepositoryManualImpl implements CampanhaDashboardRepositoryManual{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Override
	public void updateStatusAgendado(LocalDateTime data, StatusProcessoEnum status, String id) {
		Query query = new Query(new Criteria("agendamentos.data").is(data).and("_id").is(id));
		Update update = Update.update("agendamentos.$.status", status.getName());
		mongoTemplate.updateFirst(query, update, CampanhaDashboard.class);
	}


	@Override
	public void updateStatusCampanha(String id, StatusCampanhaEnum status) {
		Query query = new Query(new Criteria("_id").is(id));
		Update update = Update.update("status", status.getName());
		mongoTemplate.updateFirst(query, update, CampanhaDashboard.class);
	}

}
