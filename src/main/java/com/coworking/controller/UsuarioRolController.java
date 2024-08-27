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

import com.coworking.model.UsuarioRol;
import com.coworking.service.IUsuarioRolService;

@RestController
@RequestMapping(path = "/usuario-rol")
public class UsuarioRolController {

    @Autowired
    private IUsuarioRolService service;

    @GetMapping()
    public ResponseEntity<List<UsuarioRol>> listar()  throws Exception{
        List<UsuarioRol> usuarioRol = service.listar();
        return new ResponseEntity<List<UsuarioRol>>(usuarioRol, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> insertar(@RequestBody UsuarioRol usuarioRol)  throws Exception{
    	UsuarioRol validar = new UsuarioRol(usuarioRol.getUsuario(), usuarioRol.getRol());
        Integer resultado = service.insertar(validar);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> actualizar(@RequestBody UsuarioRol usuarioRol)  throws Exception{
        service.actualizar(usuarioRol);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id)  throws Exception{
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRol> listarPorId(@PathVariable Integer id) throws Exception{
        UsuarioRol usuarioRol = service.listarPorId(id);
        return new ResponseEntity<UsuarioRol>(usuarioRol, HttpStatus.OK);
    }

}
