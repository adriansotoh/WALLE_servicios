package com.walle.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_ticket;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_trabajador")
	private Trabajador trabajador;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_estado")
	private Estado estado;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_urgencia")
	private Urgencia urgencia;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dificultad")
	private Dificultad dificultad;
	
	private String titulo;	
	private String descripcion; 

}