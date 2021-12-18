package br.com.unipix.envio.mongo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CentroCustoMongo {
	
	private Long id;
	
	private String nome;
	
	private Integer codigo;
	
	private String status;
	
	private String solucao;
	
	private Date dataRegistro;
	
}
