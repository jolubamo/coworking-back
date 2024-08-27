package com.coworking.repository.reserva;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.coworking.model.Reserva;
import com.coworking.model.Sala;
import com.coworking.model.dto.ReservaDTO;

@Repository
public class ReservaRepository implements IReservaRepository{

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Autowired
	private ReservaMapeador mapeo;
	
	@Override
	public Integer insertar(Reserva t) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", t.getUsuario().getId());
		parameter.addValue("sala", t.getSala().getId());
		parameter.addValue("horas", t.getHoras());
		parameter.addValue("fecha", t.getFecha());

		String sql = "INSERT INTO reserva (res_usr_id, res_sal_id, res_horas, res_fecha) VALUES (:usuario, :sala, :horas,:fecha)";

		final KeyHolder holder = new GeneratedKeyHolder();
		namedJdbcTemplate.update(sql, parameter, holder);
		return holder.getKey().intValue();
	}

	@Override
	public void actualizar(Reserva t) {
		
	}

	@Override
	public void eliminar(Integer id) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("id", id);

		String sql = "DELETE FROM reserva WHERE res_id = :id;";

		namedJdbcTemplate.update(sql, parameter);
	}

	@Override
	public Reserva listarPorId(Integer id) {
		return null;
	}

	@Override
	public List<Reserva> listar() {
		String sql = "select * from reserva";
		List<Reserva> lstReserva = namedJdbcTemplate.query(sql, mapeo);
		return lstReserva;
	}

	@Override
	public List<Reserva> listarPorSala(int salaId) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("salaId", salaId);

		String sql = "select * from reserva where res_sal_id = :salaId";
		List<Reserva> lstReserva = namedJdbcTemplate.query(sql, parameter, mapeo);
		return lstReserva;
	}

	@Override
	public Integer insertar(ReservaDTO reservaDto) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", reservaDto.getUsuario().getId());
		parameter.addValue("sala", reservaDto.getSala().getId());
		parameter.addValue("horas", reservaDto.getHoras());
		parameter.addValue("fecha", reservaDto.getFecha());

		String sql = "INSERT INTO reserva (res_usr_id, res_sal_id, res_horas, res_fecha) VALUES (:usuario, :sala, :horas,:fecha)";

		final KeyHolder holder = new GeneratedKeyHolder();
		namedJdbcTemplate.update(sql, parameter, holder);
		return holder.getKey().intValue();
	}

	@Override
	public List<Reserva> listarPorUsuario(int idUsuario) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("idUsuario", idUsuario);

		String sql = "select * from reserva where res_usr_id = :idUsuario";
		List<Reserva> lstReserva = namedJdbcTemplate.query(sql, parameter, mapeo);
		return lstReserva;
	}

}
