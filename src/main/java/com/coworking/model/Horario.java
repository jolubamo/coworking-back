package com.coworking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Horario {

	private Integer id;
	private String titulo;
	private String inicio;
	private String fin;
}
