package com.coworking.repository.estado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.coworking.model.Estado;
@Repository
public class EstadoRepository implements IEstadoRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	private EstadoMapeador mapeo;

	@Override
	public List<Estado> listar() {

		String sql = "select * from estados";
		List<Estado> lstEstado = namedJdbcTemplate.query(sql, mapeo);

		return lstEstado;
	}

	@Override
	public Integer insertar(Estado estado) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", estado.getNombre());

		String sql = "INSERT INTO estados(est_nombre) VALUES(:nombre);";

		final KeyHolder holder = new GeneratedKeyHolder();
		namedJdbcTemplate.update(sql, parameter, holder);
		return holder.getKey().intValue();
	}

	@Override
	public void actualizar(Estado estado) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", estado.getNombre());
		parameter.addValue("id", estado.getId());

		String sql = "UPDATE estados SET est_nombre = :nombre WHERE est_id = :id;";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void eliminar(Integer id) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);

		String sql = "DELETE FROM estados WHERE est_id = :id;";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public Estado listarPorId(Integer id) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);
		String sql = "select * from estados where est_id = :id";
		List<Estado> lstEstado = namedJdbcTemplate.query(sql, parameter, mapeo);
		return lstEstado.get(0);
	}

}