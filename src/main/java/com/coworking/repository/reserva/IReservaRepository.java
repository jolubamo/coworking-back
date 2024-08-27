package com.coworking.repository.reserva;

import java.util.List;

import com.coworking.model.Reserva;
import com.coworking.model.dto.ReservaDTO;
import com.coworking.repository.generico.GeneralRepo;

public interface IReservaRepository extends GeneralRepo<Reserva,Integer>{

	List<Reserva> listarPorSala(int salaId);
	
	Integer insertar(ReservaDTO reservaDto);
	
	List<Reserva> listarPorUsuario(int idUsuario);
}
