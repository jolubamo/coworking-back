package com.coworking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coworking.model.TipoSala;
import com.coworking.repository.generico.GeneralRepo;
import com.coworking.repository.sala.ITipoSalaRepository;
import com.coworking.service.ITipoSalaService;

@Service
public class TipoSalaServiceImpl extends GeneralServiceImpl<TipoSala, Integer> implements ITipoSalaService{

	@Autowired
	private ITipoSalaRepository repo;
	
	@Override
	protected GeneralRepo<TipoSala, Integer> getRepo() {
		return repo;
	}

}
