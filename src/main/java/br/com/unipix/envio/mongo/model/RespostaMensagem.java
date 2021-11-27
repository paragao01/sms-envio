package br.com.unipix.envio.mongo.model;

import java.math.BigDecimal;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("respostaSms")
public class RespostaMensagem {
	@Id
	private String id;
	private Long idCampanhaSql;
	private String resposta;
	private String respostaUpperCase;
	private BigDecimal quantidade;
}
