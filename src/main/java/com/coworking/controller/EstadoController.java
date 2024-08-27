package com.coworking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coworking.model.Estado;
import com.coworking.service.IEstadoService;

@RestController
@RequestMapping(path = "/estado")
public class EstadoController {

	@Autowired
	private IEstadoService service;
	
	@GetMapping()
	public ResponseEntity<List<Estado>> listar() throws Exception {
		List<Estado> estados = service.listar();
		return new ResponseEntity<List<Estado>>(estados, HttpStatus.OK);
	}
}
