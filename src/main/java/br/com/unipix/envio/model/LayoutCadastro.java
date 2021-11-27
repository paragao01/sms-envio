package br.com.unipix.envio.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.unipix.envio.enumeration.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_layout_cadastro")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LayoutCadastro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "codigo")
	private Long codigo;
	
	@OneToOne
	@JoinColumn(name = "centro_de_custo_id", referencedColumnName = "id", nullable = false)
	private CentroCusto centroCusto;
	
	@Column(name = "numero_campos")
	private Long numeroCampos;
	
	@Column(name = "manual")
	private String manual;

	@Column(name = "tipo")
	private Integer tipo;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusEnum status;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ignorar_cabecalho")
	private StatusEnum ignorarCabecalho;
	
	@OneToOne
	@JoinColumn(name = "anexo_id", referencedColumnName = "id", nullable = true)
	private Anexo anexo;
	
	@Column(name = "layout_campanha")
	private Boolean layoutCampanha;
	
	@Column(name = "data_criacao")
	private Date dataCriacao;
	
	@OneToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private User user;
}
