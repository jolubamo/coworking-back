package com.coworking.repository.estado;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.coworking.model.Estado;

@Component

public class EstadoMapeador implements RowMapper<Estado> {

	@Override
	public Estado mapRow(ResultSet rs, int rowNum) throws SQLException {

		Integer id = rs.getInt("est_id");
		String nombre = rs.getString("est_nombre");

		return new Estado(id, nombre);

	}
}