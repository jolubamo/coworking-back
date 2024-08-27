package com.coworking.repository.tipodocumento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.coworking.model.TipoDocumento;
@Repository
public class TipoDocumentoRepository implements ITipoDocumentoRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	private TipoDocumentoMapeador mapeo;

	@Override
	public List<TipoDocumento> listar() {

		String sql = "select * from tipos_documento";

		List<TipoDocumento> lstTipoDocumento = namedJdbcTemplate.query(sql, mapeo);

		return lstTipoDocumento;

	}

	@Override
	public Integer insertar(TipoDocumento tipoDocumento) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", tipoDocumento.getNombre());

		String sql = "INSERT INTO tipos_documento(tid_nombre) VALUES (:nombre);";

		final KeyHolder holder = new GeneratedKeyHolder();
		namedJdbcTemplate.update(sql, parameter, holder);
		return holder.getKey().intValue();

	}

	@Override
	public void actualizar(TipoDocumento tipoDocumento) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", tipoDocumento.getNombre());
		parameter.addValue("id", tipoDocumento.getId());

		String sql = "UPDATE tipos_documento SET tid_nombre = :nombre WHERE tid_id = :id;";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void eliminar(Integer id) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);

		String sql = "DELETE FROM tipos_documento WHERE tid_id = :id;";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public TipoDocumento listarPorId(Integer id) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);
		String sql = "select * from tipos_documento where tid_id = :id";

		List<TipoDocumento> lstTipoDocumento = namedJdbcTemplate.query(sql, parameter, mapeo);
		return lstTipoDocumento.get(0);

	}

}