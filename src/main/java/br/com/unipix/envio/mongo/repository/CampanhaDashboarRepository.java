package br.com.unipix.envio.mongo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unipix.envio.mongo.model.CampanhaDashboard;

@Repository
public interface CampanhaDashboarRepository extends MongoRepository<CampanhaDashboard, String>, CampanhaDashboardRepositoryManual{

	@Query("{'agendamentos.data':{$lte : ?0},'status': ?1, 'confirmada':true}")
	List<CampanhaDashboard> obterCampanhasAgendadas(LocalDateTime data, String status);
	
	@Query("{'status': ?0, 'confirmada': true}")
	List<CampanhaDashboard> obterCampanhas(String status);
	
	@Query("{'status':'Pausado', 'confirmada':true, 'idCampanhaSql':?0}")
	CampanhaDashboard obterCampanhaPausada(Long idCampanhaSql);
	
	@Query("{'idCampanhaSql':?0}")
	CampanhaDashboard obterCampanha(Long idCampanhaSql);
}
	