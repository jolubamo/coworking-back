package com.coworking.service;

import java.util.List;

public interface IGeneralService<T,ID> {

	List<T> listar();

	ID insertar(T t);

	void actualizar(T t);

	void eliminar(ID id);
	
	T listarPorId(ID id);
}
