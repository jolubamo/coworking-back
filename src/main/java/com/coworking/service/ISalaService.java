package com.coworking.service;

import java.util.List;

import com.coworking.model.Sala;

public interface ISalaService extends IGeneralService<Sala, Integer>{

	List<Sala> listarPorTipoSala(int idTipoSala);
	List<Sala> listarSalasActivas();
}
