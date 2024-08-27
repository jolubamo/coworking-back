package com.coworking.service;

import java.util.List;

import com.coworking.model.Horario;

public interface IHorarioService extends IGeneralService<Horario, Integer>{

	List<Horario> listarPorId(String ids_horario);
	List<Horario> listarPorDiaYSala(String json) throws Exception;
	
}
