package com.coworking.service.impl;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coworking.exception.ForbidenException;
import com.coworking.model.Usuario;
import com.coworking.model.seguridad.Seguridad;
import com.coworking.repository.llavesecreta.ILlaveSecretaRepository;
import com.coworking.repository.seguridad.ISeguridadRepository;
import com.coworking.repository.usuario.IUsuarioRepository;
import com.coworking.security.EncriptacionTokenAux;
import com.coworking.service.ISeguridadService;
import com.coworking.util.EnviarCorreo;
import com.coworking.util.GeneradorCodigos;
import com.coworking.util.TokenHelperComponent;

@Service
public class SeguridadServiceImpl implements ISeguridadService {

	@Autowired
	private ISeguridadRepository repo;

	@Autowired
	private ILlaveSecretaRepository llaveSecretaRepository;
	
	@Autowired
	private EnviarCorreo enviarCorreo;
	
	@Autowired
	private IUsuarioRepository usuarioRepo;

	@Autowired
	private TokenHelperComponent tokenHelperComponent;

	@Override
	public String listarPorCodigo(String codigo) throws Exception {
		Seguridad seguridad = validarExistenciaDeCodigo(codigo);
		return convertirBase64(EncriptacionTokenAux.decrypt(seguridad.getPwd(), llaveSecretaRepository.listar()));
	}

	@Override
	public String generarCodigo(String token) throws Exception {
		Seguridad seguridad = new Seguridad();
		seguridad.setCodigo(GeneradorCodigos.generateRandomString(8));
		seguridad.setPwd(bytesToHex(EncriptacionTokenAux.encrypt(token, llaveSecretaRepository.listar())));
		seguridad.setFechaVencimiento(null);
		repo.insertar(seguridad);
		enviarCorreos(obtenerCorreoDelUsuario(token),seguridad.getCodigo());
		return seguridad.getCodigo();
	}
	
	private void enviarCorreos(String correo,String codigo) {

		String msj = "Codigo: ".concat(codigo);
		boolean enviado = enviarCorreo.enviarCorreo(msj, correo);
		
        if (enviado) {
            System.out.println("El correo se envió correctamente.");
        } else {
            System.out.println("Error al enviar el correo.");
        }
        
	}
	private String obtenerCorreoDelUsuario(String token ){
		String usuario=tokenHelperComponent.obtenerUsuarioDelToken(token);
		Usuario usuarioObj=usuarioRepo.info(usuario);
		String correo=usuarioObj==null?null:usuarioObj.getEmail();
		if(correo==null){
			throw new ForbidenException("El Usuario no esta Autorizado");
		}
		return correo;

	}
	private Seguridad validarExistenciaDeCodigo(String codigo) {
		if (repo.listarPorCodigo(codigo) == null)

			throw new ForbidenException("Codigo Incorrecto");

		else
			return repo.listarPorCodigo(codigo);
	}

	private String convertirBase64(String pwd) {
		byte[] bytes = pwd.getBytes();
		byte[] base64Bytes = Base64.getEncoder().encode(bytes);
		return new String(base64Bytes);
	}

	private static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}
