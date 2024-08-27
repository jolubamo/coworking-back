package com.coworking.repository.llavesecreta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LlaveSecretaRepository implements ILlaveSecretaRepository{

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public void actualizar(String llave, int id) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("llave", llave);
		parameter.addValue("id", id);
		
		String sql = "update llave_secreta set llave = :llave where llave_id = :id;";
		
		namedJdbcTemplate.update(sql, parameter);
		
	}
	
	public String listar() {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", 1);
		
		String sql = "select llave from llave_secreta where llave_id = :id";
		
		return namedJdbcTemplate.queryForObject(sql, parameter, String.class);
	}
	
}
