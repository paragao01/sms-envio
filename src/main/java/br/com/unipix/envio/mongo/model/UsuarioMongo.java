package br.com.unipix.envio.mongo.model;


import br.com.unipix.envio.enumeration.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioMongo {

	private Long id;
	
	private String nome;
	
	private String email;
	
	private StatusEnum status;
	
	private ClienteMongo cliente;
}
