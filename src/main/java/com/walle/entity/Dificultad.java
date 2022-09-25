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
@Table(name = "dificultad")
public class Dificultad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_dificultad;
	private String descripcion;
	
	

}
