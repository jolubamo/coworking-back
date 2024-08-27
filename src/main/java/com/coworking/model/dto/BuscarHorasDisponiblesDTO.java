package com.coworking.model.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuscarHorasDisponiblesDTO {

	private String fecha;
	private int idSala;
	private String disponibilidad;
}
