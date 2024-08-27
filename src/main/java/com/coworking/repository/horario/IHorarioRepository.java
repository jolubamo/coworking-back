package com.coworking.repository.horario;

import java.time.LocalDateTime;
import java.util.List;

import com.coworking.model.Horario;
import com.coworking.repository.generico.GeneralRepo;

public interface IHorarioRepository extends GeneralRepo<Horario, Integer>{

	List<Horario> listarPorId(String ids_horario);
	List<Horario> listarPorDiaYSala(String fecha, int idSala,String disponibilidad);
}
