package com.coworking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coworking.model.Estado;
import com.coworking.repository.estado.IEstadoRepository;
import com.coworking.repository.generico.GeneralRepo;
import com.coworking.service.IEstadoService;

@Service
public class EstadoServiceImpl extends GeneralServiceImpl<Estado, Integer> implements IEstadoService{

	@Autowired
	private IEstadoRepository repo;
	
	@Override
	protected GeneralRepo<Estado, Integer> getRepo() {
		return repo;
	}

}
