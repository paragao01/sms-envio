package br.com.unipix.envio.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
	INATIVO(0, "Inativo"),
	ATIVO(1, "Ativo"),
	REPETIDO(2, "Repetido"),
	INVALIDO(3, "Inválido"),
	BLACKLIST(4, "backlist");
	
	private Integer id;
	private String name;
	
	public static StatusEnum getById(Integer id) {
	    for(StatusEnum e : values()) {
	        if(e.id.equals(id)) return e;
	    }
	    throw new IllegalArgumentException(String.format("Não existe uma constante para o valor %d no ENUM %s",  id, StatusSmsEnum.class.getName()));
	}

	public String getName() {
		return name; 
	}

	public void setName(String name) {
		this.name = name;
	}
}
