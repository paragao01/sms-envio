package br.com.unipix.envio.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusSmsEnum {
	
	ENVIADO(0, "enviado"),
	AGUARDANDO_PROCESSAMENTO(1, "aguardando processamento"),
	EM_PROCESSAMENTO(2, "em processamento"),
	PAUSADO(3, "pausado"),
	ENTREGUE(4, "entregue no aparelho"),
	ESPERA(5, "espera"),
	ENVIANDO(6, "enviando");
	
	private Integer id;
	private String name;
	
	public static StatusSmsEnum getById(Integer id) {
	    for(StatusSmsEnum e : values()) {
	        if(e.id.equals(id)) return e;
	    }
	    throw new IllegalArgumentException(String.format("Não existe uma constante para o valor %d no ENUM %s",  id, StatusSmsEnum.class.getName()));
	}

}
