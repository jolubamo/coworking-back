package com.coworking.repository.sala;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.coworking.model.TipoSala;

@Component
public class TipoSalaMapeador implements RowMapper<TipoSala>{

	@Override
	public TipoSala mapRow(ResultSet rs, int rowNum) throws SQLException {
		TipoSala tipoSala = new TipoSala();
		tipoSala.setId(rs.getInt("tip_sal_id"));
		tipoSala.setNombre(rs.getString("tip_sal_nombre"));
		
		return tipoSala;
	}

}
