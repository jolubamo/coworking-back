package com.coworking.model.dto;

import java.time.LocalDateTime;

import com.coworking.model.Sala;
import com.coworking.model.Usuario;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservaDTO {

	private Integer id;
	private Usuario usuario;
	private Sala sala;
	private String horas;
	private LocalDateTime fecha;
}
