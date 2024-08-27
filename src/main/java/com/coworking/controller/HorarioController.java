package com.coworking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coworking.model.Horario;
import com.coworking.service.IHorarioService;

@RestController
@RequestMapping(path = "/horario")
public class HorarioController {

	@Autowired
	private IHorarioService service;
	
	@PostMapping(path="horarios-disponibles")
	public ResponseEntity<List<Horario>> listarPorDiaYSala(String json) throws Exception{
		List<Horario> horarios = service.listarPorDiaYSala(json);
		return new ResponseEntity<List<Horario>>(horarios, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<Horario>> listar() throws Exception {
		List<Horario> salas = service.listar();
		return new ResponseEntity<List<Horario>>(salas, HttpStatus.OK);
	}
}
