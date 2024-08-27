package com.coworking.model.seguridad;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Seguridad {
	
	private Integer id;
	private LocalDateTime fechaVencimiento;
	private String pwd;
	private String codigo;
	
	public Seguridad (Integer id, LocalDateTime fechaVencimiento, String pwd, String codigo) {
		
		this.id = id;
		this.fechaVencimiento = fechaVencimiento;
		this.pwd = pwd;
		this.codigo = codigo;
		
	}
	
	public Seguridad( LocalDateTime fechaVencimiento, String pwd, String codigo) {

		this.fechaVencimiento = fechaVencimiento;
		this.pwd = pwd;
		this.codigo = codigo;
		
	}

}
