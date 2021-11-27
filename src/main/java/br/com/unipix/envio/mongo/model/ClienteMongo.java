package br.com.unipix.envio.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteMongo {

	private Long id;
	
	private String nome;
	
	private String endereco;
	
	private String bairro;
	
	private String cidade;
	
	private String uf;
	
	private String cep;

	private String cnpj;
	
	private Boolean ativo;
}
