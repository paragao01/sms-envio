package br.com.unipix.envio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaKafka {
	
	private String smsId;

	private String numero;

	private String mensagem;

	private Long produtoId;
	
	private Long campanhaId;

}
