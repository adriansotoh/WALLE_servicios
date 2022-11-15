package com.walle.service;

import java.util.List;
import java.util.Optional;

import com.walle.entity.Trabajador;

public interface TrabajadorService {
	public abstract List<Trabajador> listar();
	public abstract Optional<Trabajador> buscarId(int id);
	public abstract List<Trabajador> listarPorRol(int id);
	
	
	
}
