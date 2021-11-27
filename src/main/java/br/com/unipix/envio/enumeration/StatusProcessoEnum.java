package br.com.unipix.envio.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusProcessoEnum {
	PROCESSANDO(0, "Processando"),
	PROCESSADO(1, "Processado"),
	FALHA(2, "Falha"),
	NAO_PROCESSADO(3, "Nao processado");
	
	private Integer id;
	private String name;
	
	public static StatusProcessoEnum getById(Integer id) {
	    for(StatusProcessoEnum e : values()) {
	        if(e.id.equals(id)) return e;
	    }
	    throw new IllegalArgumentException(String.format("Não existe uma constante para o valor %d no ENUM %s",  id, StatusProcessoEnum.class.getName()));
	}
}
