package com.walle.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id_ticket")
	private int idTicket;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_trabajador")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Trabajador trabajador ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Trabajador usuario ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estado")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Estado estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_urgencia")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Urgencia urgencia;
	 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dificultad")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

	private Dificultad dificultad;
	
	private String titulo;	
	private String descripcion; 

	private String equipo;			
	private Integer estrellas; 	
	private String opinion; 

}

