package com.coworking.repository.sala;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.coworking.model.TipoSala;

@Repository
public class TipoSalaRepository implements ITipoSalaRepository{

	@Autowired
	private TipoSalaMapeador mapeo;
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Override
	public Integer insertar(TipoSala t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(TipoSala t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TipoSala listarPorId(Integer id) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);
		String sql = "select * from tipo_sala where tip_sal_id = :id";

		List<TipoSala> lstTarea = namedJdbcTemplate.query(sql, parameter, mapeo);
		return lstTarea.get(0);
	}

	@Override
	public List<TipoSala> listar() {
		String sql = "select * from tipo_sala";
		List<TipoSala> lstSala = namedJdbcTemplate.query(sql, mapeo);
		return lstSala;
	}

}
