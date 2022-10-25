package com.walle.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.walle.entity.RolHasOpcionPK;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class RolHasOpcionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idRol;
	private int idOpcion;
}
