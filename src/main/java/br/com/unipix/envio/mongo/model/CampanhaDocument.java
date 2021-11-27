package br.com.unipix.envio.mongo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "campanha")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CampanhaDocument {

	@Id
	private String id;

	private CentroCustoMongo centroCusto;
	
	private Long idCampanhaSql;

	private String numero;

	private String mensagem;

	private ProdutoMongo produto;
	
	private LocalDateTime dataEnvio;
	
	private UsuarioMongo usuario;
	
	private Long rota;
    
    private Long fornecedor;
   
    private String mensagemFornecedor;
    
    private List<Resposta> respostas;
    
    private String status;
    
    private LocalDateTime dataAgendada;
    
    private String operadora;
    
    private Double tarifa;
    
    private LocalDateTime dataRetorno;
    
    private String situacao;
}
