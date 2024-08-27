package com.coworking.repository.seguridad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.coworking.model.seguridad.Seguridad;

@Component
public class SeguridadMapeador implements RowMapper<Seguridad> {
	
	@Override
	public Seguridad mapRow(ResultSet rs, int rowNum) throws SQLException {

		 Integer id = rs.getInt("seg_id");
		 LocalDateTime fechaVencimiento = rs.getObject("seg_fecha_vencimiento", LocalDateTime.class);
		 String pwd = rs.getString("seg_pwd");
		 String codigo = rs.getString("seg_codigo");

		return new Seguridad(id, fechaVencimiento, pwd, codigo) ;
	}

}
