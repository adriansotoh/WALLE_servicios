package com.walle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="opcion")
public class Opcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_opcion")
	private int idOpcion;
	private String descripcion;
	private String ruta;
	private int tipo;
	private int estado;
}
