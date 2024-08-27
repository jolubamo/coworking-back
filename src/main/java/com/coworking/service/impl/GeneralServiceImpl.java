package com.coworking.service.impl;

import java.util.List;

import com.coworking.repository.generico.GeneralRepo;

public abstract class GeneralServiceImpl<T,ID> implements  GeneralRepo<T, ID>{

protected abstract GeneralRepo<T,ID> getRepo();
	
	public ID insertar(T t) {
		return getRepo().insertar(t);
	}

	public void actualizar(T t) {
		getRepo().actualizar(t);
		return;
	}

	public void eliminar(ID id) {
		getRepo().eliminar(id);
		return;
	}

	public T listarPorId(ID id) {
		return getRepo().listarPorId(id);
	}

	public List<T> listar() {
		return getRepo().listar();
	}
}
