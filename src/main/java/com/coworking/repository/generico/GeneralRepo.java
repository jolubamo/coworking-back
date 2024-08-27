package com.coworking.repository.generico;

import java.util.List;

public interface GeneralRepo <T, ID> {

	ID insertar(T t);

	void actualizar(T t);

	void eliminar(ID id);

	T listarPorId(ID id);

	List<T> listar();

}

