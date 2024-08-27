package com.coworking.model;

//import com.abogados.util.ValidadorArgumento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioRol {

    private Integer id;
    private Usuario usuario;
    private Rol rol;

    public UsuarioRol(Integer id, Usuario usuario, Rol rol){
//        validarDatos(id, usuario, rol);

        this.id = id;
        this.usuario = usuario;
        this.rol = rol;
    }
    public UsuarioRol(Usuario usuario, Rol rol){

//        ValidadorArgumento.validarObligatorio(usuario, "El usuario es obligatorio");
//        ValidadorArgumento.validarObligatorio(rol, "El rol es obligatorio");

        this.usuario = usuario;
        this.rol = rol;
    }

//    private void validarDatos(Integer id, Usuario usuario, Rol rol) {
//
//        ValidadorArgumento.validarObligatorio(id, "El id es obligatorio");
//        ValidadorArgumento.validarObligatorio(usuario, "El usuario es obligatorio");
//        ValidadorArgumento.validarObligatorio(rol, "El rol es obligatorio");
//
//    }
}
