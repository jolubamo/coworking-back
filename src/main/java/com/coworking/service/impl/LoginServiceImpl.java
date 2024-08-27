package com.coworking.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coworking.model.Usuario;
import com.coworking.repository.usuario.IUsuarioRepository;

@Service
public class LoginServiceImpl implements UserDetailsService{

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Usuario usuario = usuarioRepository.buscarUsuarioClaveEstadoPorUsuario(username);

        boolean estado = false;

        if(usuario.getEstado().getId()==1) {
            estado=true;
        }

        List<GrantedAuthority> lstRole = buscarRolePorUsuario(usuario.getDocumento());

        //List<GrantedAuthority> lstRole = buscarRolePorUsuario(username);


        return new User(username, usuario.getPassword(), estado, true, true, true, lstRole);
    }

    private List<GrantedAuthority> buscarRolePorUsuario(String usuario) {

        List<String> lstStrRole = usuarioRepository.buscarRolePorUsuario(usuario);

        List<GrantedAuthority> lstGraRole=new ArrayList<GrantedAuthority>();

        for(String role:lstStrRole) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role.toUpperCase());
            lstGraRole.add(authority);
        }

        return lstGraRole;

    }

}
