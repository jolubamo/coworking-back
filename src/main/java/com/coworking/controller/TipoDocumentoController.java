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

import com.coworking.model.TipoDocumento;
import com.coworking.service.ITipoDocumentoService;

@RestController
@RequestMapping(path = "/tipo-documento")
public class TipoDocumentoController {
	
	@Autowired
	private ITipoDocumentoService service;
	
	@GetMapping()
	public ResponseEntity<List<TipoDocumento>> listar()  throws Exception{
		List<TipoDocumento> tipoDocumento = service.listar();
		return new ResponseEntity<List<TipoDocumento>>(tipoDocumento, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> insertar(@RequestBody TipoDocumento tipoDocumento) throws Exception {
		TipoDocumento validar = new TipoDocumento(tipoDocumento.getNombre());
		Integer resultado = service.insertar(validar);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

	@PutMapping()
	public ResponseEntity<?> actualizar(@RequestBody TipoDocumento tipoDocumento)  throws Exception{
		service.actualizar(tipoDocumento);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id) throws Exception {
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoDocumento> listarPorId(@PathVariable Integer id) throws Exception{
		TipoDocumento tipoDocumento = service.listarPorId(id);
		return new ResponseEntity<TipoDocumento>(tipoDocumento, HttpStatus.OK);
	}
	
	
	
}
