package com.coworking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoDocumento {

	private Integer id;
	private String nombre;
	
	public TipoDocumento(Integer id, String nombre) {
		
		this.id = id;
		this.nombre = nombre;
		
	}
	
	public TipoDocumento(String nombre) {
		
		this.nombre = nombre;
		
	}

}
