package br.com.unipix.envio.mongo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	private String status;
	private BigDecimal quantidade;
	
	@JsonProperty("percentual")
	private BigDecimal enviados;
}
