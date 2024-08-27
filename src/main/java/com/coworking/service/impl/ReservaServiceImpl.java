package com.coworking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coworking.model.Reserva;
import com.coworking.model.Usuario;
import com.coworking.model.dto.ReservaDTO;
import com.coworking.repository.generico.GeneralRepo;
import com.coworking.repository.reserva.IReservaRepository;
import com.coworking.repository.usuario.IUsuarioRepository;
import com.coworking.service.IReservaService;

@Service
public class ReservaServiceImpl extends GeneralServiceImpl<Reserva, Integer> implements IReservaService{

	@Autowired
	private IReservaRepository repo;
	
	@Autowired
	private IUsuarioRepository usuRepo;
	
	@Override
	protected GeneralRepo<Reserva, Integer> getRepo() {
		return repo;
	}
	
	@Override
	public Integer insertar(Reserva t) {
		Usuario usuario = usuRepo.info(t.getUsuario().getDocumento());
		t.setUsuario(usuario);
		
		return super.insertar(t);
	}

	@Override
	public List<Reserva> listarPorSala(int salaId) {
		return repo.listarPorSala(salaId);
	}

	@Override
	public Integer insertarDTO(ReservaDTO reservaDTO) {
		Usuario usuario = usuRepo.info(reservaDTO.getUsuario().getDocumento());
		reservaDTO.setUsuario(usuario);
		return repo.insertar(reservaDTO);
	}

	@Override
	public List<Reserva> listarPorUsuario(String documentoUsuario) {
		Usuario usuario = usuRepo.info(documentoUsuario);
		return repo.listarPorUsuario(usuario.getId());
	}

}
