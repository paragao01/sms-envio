package br.com.unipix.envio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_anexo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Anexo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String url;
	
	@Column
	private String urlWeb;

	@Column(name = "tamanho_arquivo")
	private Long tamanhoArquivo;
	
}