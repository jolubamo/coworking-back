package com.coworking.service.impl;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coworking.model.Horario;
import com.coworking.model.dto.BuscarHorasDisponiblesDTO;
import com.coworking.repository.generico.GeneralRepo;
import com.coworking.repository.horario.IHorarioRepository;
import com.coworking.service.IHorarioService;

@Service
public class HorarioServiceImpl extends GeneralServiceImpl<Horario, Integer> implements IHorarioService{

	@Autowired
	private IHorarioRepository repo;
	
	@Override
	protected GeneralRepo<Horario, Integer> getRepo() {
		return repo;
	}

	@Override
	public List<Horario> listarPorId(String ids_horario) {
		
		return repo.listarPorId(ids_horario);
	}

	@Override
	public List<Horario> listarPorDiaYSala(String json) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		BuscarHorasDisponiblesDTO dto = objectMapper.readValue(json, BuscarHorasDisponiblesDTO.class);
		return repo.listarPorDiaYSala(dto.getFecha(), dto.getIdSala(), dto.getDisponibilidad());
	}

}
