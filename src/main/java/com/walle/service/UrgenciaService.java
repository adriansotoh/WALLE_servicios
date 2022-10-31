package com.walle.service;

import java.util.List;
import java.util.Optional;

import com.walle.entity.Urgencia;

public interface UrgenciaService {
	public abstract List<Urgencia> listar();
	public abstract Optional<Urgencia> buscarId(int id);
}
