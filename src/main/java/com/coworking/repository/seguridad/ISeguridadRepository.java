package com.coworking.repository.seguridad;

import java.util.List;

import com.coworking.model.seguridad.Seguridad;

public interface ISeguridadRepository {

    List<Seguridad> listar();

    void actualizar(Seguridad seguridad);

    Seguridad listarPorCodigo(String codigo);

    void eliminar(Integer id);

    Seguridad listarPorId(Integer id);

    Integer insertar(Seguridad seguridad) throws Exception;
}
