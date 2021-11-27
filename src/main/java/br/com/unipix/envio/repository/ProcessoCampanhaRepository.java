package br.com.unipix.envio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unipix.envio.model.ProcessoCampanha;

@Repository
public interface ProcessoCampanhaRepository extends JpaRepository<ProcessoCampanha, Long>{
	
	@Query("SELECT a FROM ProcessoCampanha a WHERE a.minuto=(select max(b.minuto) from ProcessoCampanha b where b.status = 3)")
	ProcessoCampanha obterUltimoProcesso();
}
