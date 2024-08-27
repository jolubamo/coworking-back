package com.coworking.repository.horario;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.coworking.model.Horario;

@Component
public class HorarioMapeador implements RowMapper<Horario>{

	@Override
	public Horario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Horario horario = new Horario();
		horario.setId(rs.getInt("hor_id"));
		horario.setTitulo(rs.getString("hor_titulo"));
		horario.setInicio(rs.getString("hor_inicio"));
		horario.setFin(rs.getString("hor_fin"));
		return horario;
	}
	
}
