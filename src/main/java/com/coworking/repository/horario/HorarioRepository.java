package com.coworking.repository.horario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.coworking.model.Horario;

@Repository
public class HorarioRepository implements IHorarioRepository{

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Autowired
	private HorarioMapeador mapeo;
	
	@Override
	public Integer insertar(Horario t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(Horario t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Horario listarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Horario> listar() {
		String sql = "select * from horario";
		List<Horario> lstHorario = namedJdbcTemplate.query(sql, mapeo);
		return lstHorario;
	}

	@Override
	public List<Horario> listarPorId(String ids_horario) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		List<Integer> ids = Arrays.stream(ids_horario.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
		parameter.addValue("ids_horario", ids);
		String sql = "select * from horario where hor_id in (:ids_horario);";
		List<Horario> lstHorario = namedJdbcTemplate.query(sql, parameter, mapeo);
		return lstHorario;
	}

	@Override
	public List<Horario> listarPorDiaYSala(String fecha, int idSala, String disponibilidad) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		List<Integer> listaDisponibilidad = Arrays.stream(disponibilidad.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
		parameter.addValue("listaDisponibilidad", listaDisponibilidad);
		List<Integer> reservadas = listarIdsReservadosPorDia(fecha, idSala);
		parameter.addValue("listaReservadas", reservadas);
		
		String sql = "select * from horario where hor_id in (:listaDisponibilidad) ";
				
		if(!reservadas.isEmpty()) {
			sql = sql +" and hor_id not in (:listaReservadas)";
		}
		List<Horario> lstHorario = namedJdbcTemplate.query(sql, parameter, mapeo);
		return lstHorario;
	}
	
	private List<Integer> listarIdsReservadosPorDia(String fecha, int idSala){
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("fecha", fecha);
		parameter.addValue("idSala", idSala);
		String sql = "SELECT GROUP_CONCAT(res_horas ORDER BY res_horas ASC SEPARATOR ',') AS concatenated_horas FROM reserva WHERE res_sal_id = :idSala AND res_fecha = :fecha;";
		String ids = namedJdbcTemplate.queryForObject(sql,parameter, String.class);
		 if (ids == null || ids.isEmpty()) {
		        return new ArrayList<>();
		    }
		List<Integer> listaDisponibilidad = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
		return listaDisponibilidad;
	}

}
