package br.com.unipix.envio.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unipix.envio.model.ProcessoCampanha;

@Repository
public interface ProcessoCampanhaRepository extends JpaRepository<ProcessoCampanha, Long>{
	
	@Query("SELECT a FROM ProcessoCampanha a where 0=0")
	ProcessoCampanha obterUltimoProcesso();
}
