package com.walle.service;

import java.util.List;
import java.util.Optional;

import com.walle.entity.Estado;

public interface EstadoService {
	public abstract List<Estado> listar();
	public abstract Optional<Estado> buscarId(int id);
}
