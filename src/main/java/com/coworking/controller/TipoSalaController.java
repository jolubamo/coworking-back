package com.coworking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coworking.model.TipoSala;
import com.coworking.service.ITipoSalaService;

@RestController
@RequestMapping(path = "/tipo-sala")
public class TipoSalaController {

	@Autowired
	private ITipoSalaService service;
	
	@GetMapping()
	public ResponseEntity<List<TipoSala>> listar() throws Exception {
		List<TipoSala> salas = service.listar();
		return new ResponseEntity<List<TipoSala>>(salas, HttpStatus.OK);
	}
}
