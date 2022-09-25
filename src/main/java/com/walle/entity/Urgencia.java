package com.walle.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "urgencia")
public class Urgencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_urgencia;
	private String descripcion;

}
