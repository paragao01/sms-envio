package br.com.unipix.envio.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_template_mensagem")
public class TemplateMensagem {
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "codigo")
	private Integer codigo;
	
	@OneToOne
	@JoinColumn(name = "centro_custo_id", referencedColumnName = "id")
	private CentroCusto centroCusto;
	
	@Column(name = "modelo")
	private String template;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "data_alteracao")
	private Date dataAlteracao;
	
	@Column(name = "caracteres")
	private Integer caracteres;
	
	@OneToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	private Cliente cliente;

}
