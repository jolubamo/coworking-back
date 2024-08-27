package com.coworking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coworking.model.Reserva;
import com.coworking.model.dto.ReservaDTO;
import com.coworking.service.IReservaService;

@RestController
@RequestMapping(path = "/reserva")
public class ReservaController {

	@Autowired
	private IReservaService service;
	
	@GetMapping()
	public ResponseEntity<List<Reserva>> listar() throws Exception {
		List<Reserva> reservas = service.listar();
		return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
	}
	
	@GetMapping(path="/sala/{salaId}")
	public ResponseEntity<List<Reserva>> listarPorSala(@PathVariable int salaId) throws Exception {
		List<Reserva> reservas = service.listarPorSala(salaId);
		return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
	}
	
	@GetMapping(path="/usuario/{documentoUsuario}")
	public ResponseEntity<List<Reserva>> listarPorUsuario(@PathVariable String documentoUsuario) throws Exception {
		List<Reserva> reservas = service.listarPorUsuario(documentoUsuario);
		return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> insertar(@RequestBody ReservaDTO reserva) throws Exception {
		Integer resultado = service.insertarDTO(reserva);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id) throws Exception {
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
