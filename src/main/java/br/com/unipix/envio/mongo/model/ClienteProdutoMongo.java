package br.com.unipix.envio.mongo.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteProdutoMongo {
	
	private Long id;

	private BigDecimal valor;
}
