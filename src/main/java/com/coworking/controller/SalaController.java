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

import com.coworking.model.Sala;
import com.coworking.service.ISalaService;

@RestController
@RequestMapping(path = "/sala")
public class SalaController {

	@Autowired
	private ISalaService service;
	
	@GetMapping()
	public ResponseEntity<List<Sala>> listar() throws Exception {
		List<Sala> salas = service.listar();
		return new ResponseEntity<List<Sala>>(salas, HttpStatus.OK);
	}
	
	@GetMapping(path="tipo-sala/{idTipoSala}")
	public ResponseEntity<List<Sala>> listarPorTipoSala(@PathVariable("idTipoSala") int idTipoSala) throws Exception {
		List<Sala> salas = service.listarPorTipoSala(idTipoSala);
		return new ResponseEntity<List<Sala>>(salas, HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<?> actualizar(@RequestBody Sala sala)  throws Exception{
		service.actualizar(sala);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id) throws Exception {
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> insertar(@RequestBody Sala sala)  throws Exception{
		Integer resultado = service.insertar(sala);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@GetMapping(path="activas")
	public ResponseEntity<List<Sala>> listarActivas() throws Exception {
		List<Sala> salas = service.listarSalasActivas();
		return new ResponseEntity<List<Sala>>(salas, HttpStatus.OK);
	}
}
