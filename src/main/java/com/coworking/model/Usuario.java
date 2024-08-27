package com.coworking.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {

	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private String documento;
	private TipoDocumento tipoDocumento;
	private Estado estado;
	
	public Usuario(Integer id, String nombre, String apellido, String email, String password, String documento, TipoDocumento tipoDocumento, Estado estado) {
		
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
		this.estado = estado;
		
	}
	
	public Usuario(String nombre, String apellido, String email, String password, String documento, TipoDocumento tipoDocumento, Estado estado) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
		this.estado = estado;
		
	}
	
	public Usuario(String nombre, String password, String documento, TipoDocumento tipoDocumento, Estado estado) {
		
		this.nombre = nombre;
		this.password = password;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
		this.estado = estado;
		
	}
	
	public Usuario(Integer id, String nombre, String password, String documento, TipoDocumento tipoDocumento, Estado estado) {
		
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
		this.estado = estado;
		
	}

}
