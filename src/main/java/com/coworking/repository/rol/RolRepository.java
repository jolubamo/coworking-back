package com.coworking.repository.rol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.coworking.model.Rol;

@Repository
public class RolRepository implements IRolRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	private RolMapeador mapeo;

	@Override
	public List<Rol> listar() {

		String sql = "select * from roles";
		List<Rol> lstRol = namedJdbcTemplate.query(sql, mapeo);

		return lstRol;

	}

	@Override
	public Integer insertar(Rol rol) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", rol.getNombre());

		String sql = "INSERT INTO roles(rol_nombre) VALUES(:nombre);";

		final KeyHolder holder = new GeneratedKeyHolder();
		namedJdbcTemplate.update(sql, parameter, holder);
		return holder.getKey().intValue();

	}

	@Override
	public void actualizar(Rol rol) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", rol.getNombre());
		parameter.addValue("id", rol.getId());

		String sql = "UPDATE roles SET rol_nombre = :nombre WHERE rol_id = :id;";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void eliminar(Integer id) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);

		String sql = "DELETE FROM roles WHERE rol_id = :id;";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public Rol listarPorId(Integer id) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);
		String sql = "select * from roles where rol_id = :id";

		List<Rol> lstRol = namedJdbcTemplate.query(sql, parameter, mapeo);
		return lstRol.get(0);

	}

}