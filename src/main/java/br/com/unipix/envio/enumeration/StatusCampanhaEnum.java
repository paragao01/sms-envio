package br.com.unipix.envio.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCampanhaEnum {
	ENVIANDO(0, "Enviando"),
	COMPLETO(1, "Completo"),
	AGENDADO(3, "Agendado"),
	ENVIADO(4, "Enviado"),
	CANCELADO(5, "Cancelado"),
	PAUSADO(2, "Pausado");
	
	private Integer id;
	private String name;

}
