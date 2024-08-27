package com.coworking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coworking.model.UsuarioRol;
import com.coworking.repository.generico.GeneralRepo;
import com.coworking.repository.usuariorol.IUsuarioRolRepository;
import com.coworking.service.IUsuarioRolService;

@Service
public class UsuarioRolServiceImpl extends GeneralServiceImpl<UsuarioRol, Integer> implements IUsuarioRolService {

    @Autowired
    IUsuarioRolRepository repo;

    @Override
    protected GeneralRepo<UsuarioRol, Integer> getRepo() { return repo; }

}
