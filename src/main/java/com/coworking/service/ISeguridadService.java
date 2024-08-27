package com.coworking.service;

public interface ISeguridadService  {
    String listarPorCodigo(String codigo) throws Exception;

    String generarCodigo(String token) throws Exception;
}
