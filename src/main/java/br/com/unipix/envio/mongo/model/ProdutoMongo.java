package br.com.unipix.envio.mongo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoMongo {

	private Long id;
	
	private String nome;
	
	private Integer rota;
	
	private List<ClienteProdutoMongo> clienteProduto;
}
