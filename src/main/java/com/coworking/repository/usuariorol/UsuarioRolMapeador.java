package com.coworking.repository.usuariorol;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.coworking.model.Rol;
import com.coworking.model.Usuario;
import com.coworking.model.UsuarioRol;
import com.coworking.repository.rol.IRolRepository;
import com.coworking.repository.usuario.IUsuarioRepository;

@Component
public class UsuarioRolMapeador implements RowMapper<UsuarioRol> {

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private IRolRepository rolRepo;

    @Override
    public UsuarioRol mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("usr_id");
        Usuario usuario = usuarioRepo.listarPorId(rs.getInt("usu_id"));
        Rol rol = rolRepo.listarPorId(rs.getInt("rol_id"));

        return new UsuarioRol(id, usuario, rol);
    }
}
