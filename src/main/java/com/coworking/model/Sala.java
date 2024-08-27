package com.coworking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Sala {

	private Integer id;
	private String nombre;
	private TipoSala tipoSala;
	private String capacidad;
	private String equipamiento;
	private String disponibilidad;
	private Estado estado;
	
}
