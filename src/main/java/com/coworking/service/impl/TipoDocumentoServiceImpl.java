package com.coworking.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coworking.model.TipoDocumento;
import com.coworking.repository.generico.GeneralRepo;
import com.coworking.repository.tipodocumento.ITipoDocumentoRepository;
import com.coworking.service.ITipoDocumentoService;

@Service

public class TipoDocumentoServiceImpl extends GeneralServiceImpl<TipoDocumento,Integer>   implements ITipoDocumentoService{
	
	@Autowired
	private ITipoDocumentoRepository repo;

	@Override
	protected GeneralRepo<TipoDocumento,Integer> getRepo(){
		return repo;
	}

}
