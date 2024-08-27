package com.coworking.repository.sala;

import java.util.List;

import com.coworking.model.Sala;
import com.coworking.repository.generico.GeneralRepo;

public interface ISalaRepository extends GeneralRepo<Sala, Integer>{

	List<Sala> listarPorTipoSala(int idTipoSala);
	List<Sala> listarSalasActivas();
}
