package br.com.unipix.envio.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCampanhaEnum {
	ENVIANDO(0, "Enviando"),
	COMPLETO(1, "Completo"),
	AGENDADO(3, "Agendado"),
	PAUSADO(2, "Pausado");
	
	private Integer id;
	private String name;

}
