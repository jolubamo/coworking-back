package com.coworking.service;

import java.util.List;

import com.coworking.model.Reserva;
import com.coworking.model.dto.ReservaDTO;

public interface IReservaService extends IGeneralService<Reserva, Integer>{

	List<Reserva> listarPorSala(int salaId);
	
	Integer insertarDTO(ReservaDTO reservaDTO);
	
	List<Reserva> listarPorUsuario(String documentoUsuario);
}
