package com.coworking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Estado {

	private Integer id;
	private String nombre;

	public Estado(Integer id, String nombre) {
		
		this.id = id;
		this.nombre = nombre;
		
	}
	
	public Estado(String nombre) {
		
		this.nombre = nombre;
		
	}

}
