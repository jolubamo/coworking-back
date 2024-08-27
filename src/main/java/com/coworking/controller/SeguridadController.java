package com.coworking.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coworking.service.ISeguridadService;

@RestController
@RequestMapping(path = "/seguridad")
public class SeguridadController {

    @Autowired
    private ISeguridadService service;

    @PostMapping()
    public ResponseEntity<String> listar(@RequestBody Map<String,Object> objetoValidar) throws Exception {
        String seguridad = service.generarCodigo(objetoValidar.get("pwd").toString());
        return new ResponseEntity<>(seguridad, HttpStatus.OK);
    }

    @GetMapping("validar")
    public ResponseEntity<Map<String, String>> validar(@RequestParam("codigo") String codigo) throws Exception {
    	Map<String, String> resultado=new HashMap<>();
    	resultado.put("data",  service.listarPorCodigo(codigo));
        
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }
}
