package com.coworking.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.coworking.exception.ExcepcionArgumento;
import com.coworking.model.Usuario;
import com.coworking.model.UsuarioRol;
import com.coworking.repository.generico.GeneralRepo;
import com.coworking.repository.usuario.IUsuarioRepository;
import com.coworking.repository.usuariorol.IUsuarioRolRepository;
import com.coworking.service.IUsuarioService;
import com.coworking.util.TokenHelperComponent;

@Service
public class UsuarioServiceImpl extends GeneralServiceImpl<Usuario, Integer> implements IUsuarioService {

    @Autowired
    private IUsuarioRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private TokenHelperComponent token;

    @Autowired
    private IUsuarioRolRepository usuRolRepo;

    @Override
    protected GeneralRepo<Usuario, Integer> getRepo() {
        return repo;
    }

    @Override
    public Integer insertar(Usuario usuario) {
        validarDocumentoNoRepetido(usuario.getDocumento());
        return repo.insertar(usuario);
    }

    @Override
    public Usuario buscarUsuarioClaveEstadoPorUsuario(String username) {
        return repo.buscarUsuarioClaveEstadoPorUsuario(username);
    }

    @Override
    public List<String> buscarRolePorUsuario(String usuario) {
        return repo.buscarRolePorUsuario(usuario);
    }

    @Override
    public void actualizarConRol(UsuarioRol usuarioRol) {
        validarClaveNORepetida(usuarioRol.getUsuario().getPassword(), usuarioRol.getUsuario().getId());

        Usuario usuViejo = repo.listarPorId(usuarioRol.getUsuario().getId());

        if (!usuViejo.getDocumento().equals(usuarioRol.getUsuario().getDocumento())) {
            validarDocumentoNoRepetido(usuarioRol.getUsuario().getDocumento());
//            validarDocumentoNoRepetidoCliente(usuarioRol.getUsuario().getDocumento());
        }
        usuRolRepo.actualizar(usuarioRol);

        this.repo.actualizar(usuarioRol.getUsuario());

    }

    private void validarClaveNORepetida(String clave, Integer id) {
        Usuario usuario = this.repo.listarPorId(id);
        if (encoder.matches(clave, usuario.getPassword()))
            throw new ExcepcionArgumento("La contraseña que Ingreso no puede ser la misma ingresada anteriormente");

    }

    private void validarDocumentoNoRepetido(String documento) {
        if (repo.info(documento) != null)
            throw new ExcepcionArgumento("Ya existe este documento en Usuario");

    }


    @Override
    public Usuario info(HttpServletRequest request) {

        return repo.info(token.obtenerUsuarioDelToken(request.getHeader("Authorization")));
    }
}