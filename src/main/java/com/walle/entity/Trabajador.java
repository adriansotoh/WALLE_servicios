package com.walle.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "trabajador")
public class Trabajador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_trabajador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rol")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Rol rol;
	
	private String documento;
	private String nombres;
	private String apellidos;
	private String correo;
	private String descripcion;
	private String usuario;

}
