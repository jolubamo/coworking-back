package com.coworking.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coworking.model.Usuario;
import com.coworking.model.UsuarioRol;
import com.coworking.service.IUsuarioRolService;
import com.coworking.service.IUsuarioService;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private IUsuarioRolService usuRolService;
	
	@GetMapping()
	public ResponseEntity<List<Usuario>> listar()  throws Exception{
		List<Usuario> usuario = service.listar();
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> insertar(@RequestBody UsuarioRol usuarioRol)  throws Exception{
		
		Usuario validarUsuario = new Usuario(usuarioRol.getUsuario().getNombre(), usuarioRol.getUsuario().getApellido(), usuarioRol.getUsuario().getEmail(), usuarioRol.getUsuario().getPassword(), 
				usuarioRol.getUsuario().getDocumento(), usuarioRol.getUsuario().getTipoDocumento(), usuarioRol.getUsuario().getEstado());		
		Integer usuarioId = service.insertar(validarUsuario);
		validarUsuario.setId(usuarioId);
		
		UsuarioRol validarUsuarioRol = new UsuarioRol(validarUsuario, usuarioRol.getRol());
		
		Integer resultado = usuRolService.insertar(validarUsuarioRol);
		
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}


	@PutMapping()
	public ResponseEntity<?> actualizar(@RequestBody UsuarioRol usuarioRol)  throws Exception{
		
		service.actualizarConRol(usuarioRol);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id)  throws Exception{
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> listarPorId(@PathVariable Integer id) throws Exception{
		Usuario usuario = service.listarPorId(id);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@GetMapping("info")
	public ResponseEntity<Usuario> info(HttpServletRequest request) throws Exception{
		Usuario usuario = service.info(request);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
}
