package com.coworking.repository.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.coworking.model.seguridad.Seguridad;
import com.coworking.repository.llavesecreta.ILlaveSecretaRepository;

@Repository
public class SeguridadRepository implements ISeguridadRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Autowired
	private SeguridadMapeador mapeo;

	@Autowired
	private ILlaveSecretaRepository llaveSecretaRepository;
	
	@Override
	public List<Seguridad> listar() {

		String sql = "select * from seguridad";
		return namedJdbcTemplate.query(sql,mapeo);
	}
	
	@Override
	public Integer insertar(Seguridad seguridad) throws Exception {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("fechaVencimiento", seguridad.getFechaVencimiento());
		parameter.addValue("pwd", seguridad.getPwd());
		parameter.addValue("codigo", seguridad.getCodigo());

		String sql = "INSERT INTO seguridad(seg_fecha_vencimiento, seg_pwd, seg_codigo) VALUES (:fechaVencimiento, :pwd, :codigo);";

		final KeyHolder holder = new GeneratedKeyHolder();
		namedJdbcTemplate.update(sql, parameter, holder);
		return holder.getKey().intValue();

	}
	
	@Override
	public void actualizar(Seguridad seguridad) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", seguridad.getId());
		parameter.addValue("fechaVencimiento", seguridad.getFechaVencimiento());
		parameter.addValue("pwd", seguridad.getPwd());
		parameter.addValue("codigo", seguridad.getCodigo());

		String sql = "UPDATE seguridad SET seg_fecha_vencimiento = :fechaVencimiento, seg_pwd = :pwd, seg_codigo = :codigo WHERE seg_id = :id;";

		namedJdbcTemplate.update(sql, parameter);

	}
	
	@Override
	public void eliminar(Integer id) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);

		String sql = "DELETE FROM seguridad WHERE seg_id = :id;";

		namedJdbcTemplate.update(sql, parameter);

	}
	
	@Override
	public Seguridad listarPorId(Integer id) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);
		String sql = "select * from seguridad where seg_id = :id";
		List<Seguridad> lstSeguridad = namedJdbcTemplate.query(sql,parameter, mapeo);

		return lstSeguridad.get(0);
	}
	@Override
	public Seguridad listarPorCodigo(String codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);
		String sql = "select * from seguridad where seg_codigo = :codigo";
		List<Seguridad> lstSeguridad = namedJdbcTemplate.query(sql,parameter, mapeo);

		return lstSeguridad.isEmpty()?null: lstSeguridad.get(0);
	}
}
