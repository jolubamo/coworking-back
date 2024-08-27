package com.coworking.exception;

public class ModeloNotFoundException extends RuntimeException {

	public ModeloNotFoundException(String mensaje) {
		super(mensaje);
	}
}
