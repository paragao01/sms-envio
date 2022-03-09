package br.com.unipix.envio.mongo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "campanhaDashboard")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CampanhaDashboard {
	
	@Id
	private String id;
	
	private String nomeCampanha;
	
	private Long idCampanhaSql;
	
	private Long cancelado;
	
	private Long blackList;
	
	private Long invalidos;
	
	private Long repetidos;
	
	private Long validos;
	
	private Long clienteId;
	
	private Long total;
	
	private Long idCentroCusto;
	
	private Long enviados;
	
	private Long enviosUnitarios;
	
	private LocalDateTime dataCampanha;
	
	private LocalDateTime dataFinalizacao;
	
	private List<CampanhaAgendada> agendamentos;
	
	private String status;
	
	private boolean confirmada;
	
}
