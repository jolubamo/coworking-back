package com.coworking.repository.usuario;


import java.util.List;

import com.coworking.model.Usuario;
import com.coworking.repository.generico.GeneralRepo;

public interface IUsuarioRepository extends GeneralRepo<Usuario,Integer>{

	Usuario buscarUsuarioClaveEstadoPorUsuario(String username);

	List<String> buscarRolePorUsuario(String usuario);
	
	Usuario info(String usuario);

	void actualizarDocumentoUsuario(String documentoViejo, String documento);

}
