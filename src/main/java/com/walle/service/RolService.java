package com.walle.service;

import java.util.List;
import java.util.Optional;

import com.walle.entity.Rol;

public interface RolService {
	public abstract List<Rol> listar();
	public abstract Optional<Rol> buscarId(int id);
}
