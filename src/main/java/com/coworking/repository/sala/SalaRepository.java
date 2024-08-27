package com.coworking.repository.sala;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.coworking.model.Sala;

@Repository
public class SalaRepository implements ISalaRepository{

	@Autowired
	private SalaMapeador mapeo;
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Override
	public Integer insertar(Sala t) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", t.getNombre());
		parameter.addValue("tipoSala", t.getTipoSala().getId());
		parameter.addValue("capacidad", t.getCapacidad());
		parameter.addValue("equipamiento", t.getEquipamiento());
		parameter.addValue("disponibilidad", t.getDisponibilidad());
		parameter.addValue("estado", t.getEstado().getId());
		String sql = "INSERT INTO sala ( sal_nombre, tip_sal_id, sal_capacidad, sal_equipamiento, sal_disponibilidad, sal_est_id) VALUES (:nombre, :tipoSala, :capacidad, :equipamiento, :disponibilidad, :estado)";
		final KeyHolder holder = new GeneratedKeyHolder();
		namedJdbcTemplate.update(sql, parameter, holder);
		return holder.getKey().intValue();
	}

	@Override
	public void actualizar(Sala t) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", t.getNombre());
		parameter.addValue("tipoSala", t.getTipoSala().getId());
		parameter.addValue("capacidad", t.getCapacidad());
		parameter.addValue("equipamiento", t.getEquipamiento());
		parameter.addValue("disponibilidad", t.getDisponibilidad());
		parameter.addValue("estado", t.getEstado().getId());
		parameter.addValue("id", t.getId());
		String sql = "UPDATE sala SET sal_nombre = :nombre, tip_sal_id = :tipoSala, sal_capacidad = :capacidad, "
				+ "sal_equipamiento = :equipamiento, sal_disponibilidad = :disponibilidad, sal_est_id = :estado WHERE sal_id = :id;";

		namedJdbcTemplate.update(sql, parameter);
	}

	@Override
	public void eliminar(Integer id) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);

		String sql = "DELETE FROM sala WHERE sal_id = :id;";

		namedJdbcTemplate.update(sql, parameter);
	}

	@Override
	public Sala listarPorId(Integer id) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);
		String sql = "select * from sala where sal_id = :id";

		List<Sala> lstTarea = namedJdbcTemplate.query(sql, parameter, mapeo);
		return lstTarea.get(0);
	}

	@Override
	public List<Sala> listar() {
		String sql = "select * from sala";
		List<Sala> lstSala = namedJdbcTemplate.query(sql, mapeo);
		return lstSala;
	}

	@Override
	public List<Sala> listarPorTipoSala(int idTipoSala) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("idTipoSala", idTipoSala);
		String sql = "select * from sala where tip_sal_id = :idTipoSala and sal_est_id = 1 ";
		List<Sala> lstSala = namedJdbcTemplate.query(sql, parameter,mapeo);
		return lstSala;
	}

	@Override
	public List<Sala> listarSalasActivas() {
		String sql = "select * from sala where sal_est_id = 1";
		List<Sala> lstSala = namedJdbcTemplate.query(sql, mapeo);
		return lstSala;
	}

}
