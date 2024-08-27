package com.coworking.repository.reserva;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.coworking.model.Reserva;
import com.coworking.repository.horario.IHorarioRepository;
import com.coworking.repository.sala.ISalaRepository;
import com.coworking.repository.usuario.IUsuarioRepository;

@Component
public class ReservaMapeador implements RowMapper<Reserva> {

	@Autowired
	private IHorarioRepository horarioRepo;
	
	@Autowired
	private ISalaRepository salaRepo;
	
	@Autowired
	private IUsuarioRepository usuRepo;
	
	@Override
	public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Reserva reserva = new Reserva();
		reserva.setFecha(rs.getObject("res_fecha", LocalDateTime.class));
		reserva.setHoras(horarioRepo.listarPorId(rs.getString("res_horas")));
		reserva.setId(rs.getInt("res_id"));
		reserva.setSala(salaRepo.listarPorId(rs.getInt("res_sal_id")));
		reserva.setUsuario(usuRepo.listarPorId(rs.getInt("res_usr_id")));
		
		return reserva;
	}

	
}
