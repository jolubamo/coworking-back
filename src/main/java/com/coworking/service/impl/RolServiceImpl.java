package com.coworking.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coworking.model.Rol;
import com.coworking.repository.generico.GeneralRepo;
import com.coworking.repository.rol.IRolRepository;
import com.coworking.service.IRolService;

@Service
public class RolServiceImpl extends GeneralServiceImpl<Rol,Integer>  implements IRolService{
	
	@Autowired
	private IRolRepository repo;

	@Override
	protected GeneralRepo<Rol,Integer> getRepo(){
		return repo;
	}


}
