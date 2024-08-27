package com.coworking.model;


//import com.abogados.util.ValidadorArgumento;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Rol {

	private Integer id;
	private String nombre;
	
	public Rol(Integer id, String nombre) {
		
//		validarDatos(id, nombre);
		
		this.id = id;
		this.nombre = nombre;
		
	}
	
	public Rol(String nombre) {
		
//		ValidadorArgumento.validarObligatorio(nombre, "El nombre es obligatorio");
		
		this.nombre = nombre;
		
	}
	
//	private void validarDatos(Integer id, String nombre) {
//
//		ValidadorArgumento.validarObligatorio(id, "El id es obligatorio");
//		ValidadorArgumento.validarObligatorio(nombre, "El nombre es obligatorio");
//
//	}

}
