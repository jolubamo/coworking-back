package com.coworking.repository.rol;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.coworking.model.Rol;

@Component
public class RolMapeador implements RowMapper<Rol> {

	@Override
	public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {

		Integer id = rs.getInt("rol_id");
		String nombre = rs.getString("rol_nombre");

		return new Rol(id, nombre);
	}
}