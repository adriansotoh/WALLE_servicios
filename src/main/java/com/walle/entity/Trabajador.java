package com.walle.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "trabajador")
public class Trabajador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_trabajador;
	private int id_rol;
	private String documento;
	private String nombres;
	private String apellidos;
	private String correo;

}