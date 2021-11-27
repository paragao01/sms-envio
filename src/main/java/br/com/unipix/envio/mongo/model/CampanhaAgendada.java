package br.com.unipix.envio.mongo.model;

import java.time.LocalDateTime;

import br.com.unipix.envio.enumeration.StatusProcessoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CampanhaAgendada {
	private LocalDateTime data;
	private StatusProcessoEnum status;
}
