package com.coworking.controller;

import java.util.List;

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

import com.coworking.model.Rol;
import com.coworking.service.IRolService;

@RestController
@RequestMapping(path = "/rol")
public class RolController {
	
	@Autowired
	private IRolService service;
	
	@GetMapping()
	public ResponseEntity<List<Rol>> listar()  throws Exception{
		List<Rol> rol = service.listar();
		return new ResponseEntity<List<Rol>>(rol, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> insertar(@RequestBody Rol rol)  throws Exception{
		Rol validar = new Rol(rol.getNombre());
		Integer resultado = service.insertar(validar);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

	@PutMapping()
	public ResponseEntity<?> actualizar(@RequestBody Rol rol)  throws Exception{
		service.actualizar(rol);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id) throws Exception {
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Rol> listarPorId(@PathVariable Integer id) throws Exception{
		Rol rol = service.listarPorId(id);
		return new ResponseEntity<Rol>(rol, HttpStatus.OK);
	}
	
}
