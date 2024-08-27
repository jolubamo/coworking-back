package com.coworking.repository.usuariorol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.coworking.model.UsuarioRol;

@Repository
public class UsuarioRolRepository implements IUsuarioRolRepository{

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private UsuarioRolMapeador mapeo;

    @Override
    public List<UsuarioRol> listar() {

        String sql = "select * from usuarios_rol";
        List<UsuarioRol> lstUsuarioRol = namedJdbcTemplate.query(sql, mapeo);

        return lstUsuarioRol;

    }

    @Override
    public Integer insertar(UsuarioRol usuarioRol) {

        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("idUsuario", usuarioRol.getUsuario().getId());
        parameter.addValue("idRol", usuarioRol.getRol().getId());


        String sql = "INSERT INTO usuarios_rol(usu_id, rol_id) VALUES(:idUsuario, :idRol);";

        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(sql, parameter, holder);
        return holder.getKey().intValue();

    }

    @Override
    public void actualizar(UsuarioRol usuarioRol) {

        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", usuarioRol.getId());
        parameter.addValue("idUsuario", usuarioRol.getUsuario().getId());
        parameter.addValue("idRol", usuarioRol.getRol().getId());

        String sql = "UPDATE usuarios_rol SET usu_id = :idUsuario, rol_id = :idRol WHERE usr_id = :id;";

        namedJdbcTemplate.update(sql, parameter);

    }

    @Override
    public void eliminar(Integer id) {

        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", id);

        String sql = "DELETE FROM usuarios_rol WHERE usr_id = :id;";

        namedJdbcTemplate.update(sql, parameter);

    }

    @Override 
    public UsuarioRol listarPorId(Integer id) {

        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", id);
        String sql = "select * from usuarios_rol where usu_id = :id";

        List<UsuarioRol> lstUsuarioRol = namedJdbcTemplate.query(sql, parameter, mapeo);

        return lstUsuarioRol.get(0);

    }
}
