package com.coworking.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.coworking.model.Usuario;
import com.coworking.model.UsuarioRol;

public interface IUsuarioService extends IGeneralService<Usuario, Integer>{

	Usuario buscarUsuarioClaveEstadoPorUsuario(String email);

	List<String> buscarRolePorUsuario(String email);
	
	Usuario info(HttpServletRequest request);
	
	void actualizarConRol(UsuarioRol usuarioRol);

}
