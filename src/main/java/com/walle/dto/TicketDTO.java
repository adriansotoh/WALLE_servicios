package com.walle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
	
	private int idTicket;
	private int idTrabajador;
	private int idEstado;
	private int idUrgencia;
	private int idDificultad;
	private String titulo;
	private String descripcion;
	private String equipo;
	private int estrellas;
	private String opinion;
	private int id_usuario; 
}
