package com.coworking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coworking.model.Sala;
import com.coworking.repository.generico.GeneralRepo;
import com.coworking.repository.sala.ISalaRepository;
import com.coworking.service.ISalaService;

@Service
public class SalaServiceImpl extends GeneralServiceImpl<Sala, Integer> implements ISalaService{

	@Autowired
	private ISalaRepository repo;
	
	@Override
	protected GeneralRepo<Sala, Integer> getRepo() {
		return repo;
	}

	@Override
	public List<Sala> listarPorTipoSala(int idTipoSala) {
		return repo.listarPorTipoSala(idTipoSala);
	}

	@Override
	public List<Sala> listarSalasActivas() {
		return repo.listarSalasActivas();
	}

}
