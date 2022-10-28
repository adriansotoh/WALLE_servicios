package com.walle.service;

import java.util.List;
import java.util.Optional;

import com.walle.entity.Dificultad;

public interface DificultadService {

	public abstract List<Dificultad> listar();
	public abstract Optional<Dificultad> buscarId(int id);
}
