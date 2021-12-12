package br.com.unipix.envio.mongo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unipix.envio.mongo.model.CampanhaDocument;

@Repository
public interface CampanhaDocumentRepository extends MongoRepository<CampanhaDocument, String>, CampanhaDocumentRepositoryManual{
	
	@Query(value = "{'dataAgendada': {$lte:?0},'idCampanhaSql':?1, 'status': ?2}")
	List<CampanhaDocument> buscarSmsAgendado(LocalDateTime data, Long idCampanha, String status, Pageable page);
	
	@Query(value = "{'idCampanhaSql':?0, 'status':?1}")
	List<CampanhaDocument> buscarSms(Long idCampanha, String status, Pageable page);
	

	@Query(value = "{'idCampanhaSql':?0, 'status':?1}" )
	List<CampanhaDocument> buscarSms(Long idCampanha, String status);
}
                                                                              