package com.coworking.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Reserva {

	private Integer id;
	private Usuario usuario;
	private Sala sala;
	private List<Horario> horas;
	private LocalDateTime fecha;
}
