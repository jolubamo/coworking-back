package com.coworking.repository.sala;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.coworking.model.Sala;
import com.coworking.repository.estado.IEstadoRepository;

@Component
public class SalaMapeador implements RowMapper<Sala>{

	@Autowired
	private ITipoSalaRepository tipoSalaRepo;
	
	@Autowired
	private IEstadoRepository estadoRepo;
	
	@Override
	public Sala mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sala sala = new Sala();
		sala.setCapacidad(rs.getString("sal_capacidad"));
		sala.setDisponibilidad(rs.getString("sal_disponibilidad"));
		sala.setEquipamiento(rs.getString("sal_equipamiento"));
		sala.setId(rs.getInt("sal_id"));
		sala.setNombre(rs.getString("sal_nombre"));
		sala.setTipoSala(tipoSalaRepo.listarPorId(rs.getInt("tip_sal_id")));
		sala.setEstado(estadoRepo.listarPorId(rs.getInt("sal_est_id")));
		
		return sala;
	}

	
}
