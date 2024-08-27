package com.coworking.repository.usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.coworking.exception.ForbidenException;
import com.coworking.model.Estado;
import com.coworking.model.Usuario;
@Repository
public class UsuarioRepository implements IUsuarioRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	private UsuarioMapeador mapeo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;


	@Override
	public List<Usuario> listar() {

		String sql = "select * from usuarios";
		return namedJdbcTemplate.query(sql, mapeo);

	}

	@Override
	public Integer insertar(Usuario usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		
		parameter.addValue("nombre", usuario.getNombre());
		parameter.addValue("apellido", usuario.getApellido());
		parameter.addValue("email", usuario.getEmail());
		parameter.addValue("documento", usuario.getDocumento());
		parameter.addValue("password", encoder.encode(usuario.getPassword()));
		parameter.addValue("idTid", usuario.getTipoDocumento().getId());
		parameter.addValue("idEstado", usuario.getEstado().getId());

		String sql = "INSERT INTO usuarios(usu_nombre, usu_apellido, usu_documento, usu_email, usu_password, tid_id, est_id) "
				+ "VALUES(:nombre, :apellido, :documento, :email, :password, :idTid, :idEstado);";

		final KeyHolder holder = new GeneratedKeyHolder();
		namedJdbcTemplate.update(sql, parameter, holder);
		return holder.getKey().intValue();

	}

	@Override
	public void actualizar(Usuario usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", usuario.getNombre());
		parameter.addValue("apellido", usuario.getApellido());
		parameter.addValue("email", usuario.getEmail());
		parameter.addValue("documento", usuario.getDocumento());
		parameter.addValue("password", encoder.encode(usuario.getPassword()));
		parameter.addValue("idTid", usuario.getTipoDocumento().getId());
		parameter.addValue("id", usuario.getId());
		parameter.addValue("idEstado", usuario.getEstado().getId());

		String sql = "UPDATE usuarios SET usu_nombre = :nombre, usu_apellido = :apellido, usu_documento = :documento, "
				+ "usu_email = :email, usu_password = :password, tid_id = :idTid, est_id = :idEstado WHERE usu_id = :id;";

		namedJdbcTemplate.update(sql, parameter);

	}
	
	@Override
	public void actualizarDocumentoUsuario(String documentoViejo, String documento) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("documentoViejo", documentoViejo);
		parameter.addValue("documento", documento);

		String sql = "UPDATE usuarios SET usu_documento = :documento WHERE usu_documento = :documentoViejo;";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void eliminar(Integer id) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);

		String sql = "DELETE FROM usuarios WHERE usu_id = :id;";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public Usuario listarPorId(Integer id) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);
		String sql = "select * from usuarios where usu_id = :id";

		List<Usuario> lstUsuario = namedJdbcTemplate.query(sql, parameter, mapeo);

		if (lstUsuario.size() != 0) {
			return lstUsuario.get(0);
		}else {
			return null;
		}

	}

	@Override
	public Usuario buscarUsuarioClaveEstadoPorUsuario(String documento) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("documento", documento);

		String sql = "select * from usuarios u inner join estados e on e.est_id = u.est_id where usu_documento= :documento ";

		List<Usuario> lstUsuario = namedJdbcTemplate.query(sql, parameter, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario user = new Usuario();
				Estado estado = new Estado();
				estado.setId(rs.getInt("est_id"));
				user.setPassword(rs.getString("usu_password"));
				user.setDocumento(rs.getString("usu_documento"));
				user.setEstado(estado);
				return user;
			}

		});

		if (lstUsuario.size() == 0) {
			throw new ForbidenException("el usuario no existe -> " + documento);
		}

		return lstUsuario.get(0);
	}

	@Override
	public List<String> buscarRolePorUsuario(String documento) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("documento", documento);

		String sql = "select rol_nombre from roles r inner join usuarios_rol ur on ur.rol_id = r.rol_id inner join usuarios u on ur.usu_id = u.usu_id where u.usu_documento=:documento";

		List<String> lstRoles = namedJdbcTemplate.query(sql, parameter, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				return rs.getString("rol_nombre");
			}

		});

		if (lstRoles.size() == 0) {
			throw new ForbidenException("Usuario sin permisos -> " + documento);
		}

		return lstRoles;
	}

	@Override
	public Usuario info(String usuario) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", usuario);
		String sql = "select * from usuarios where usu_documento = :usuario";

		List<Usuario> lstUsuario = namedJdbcTemplate.query(sql, parameter, mapeo);

		return lstUsuario.isEmpty()?null:lstUsuario.get(0);
	}

}