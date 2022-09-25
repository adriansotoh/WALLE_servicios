package com.walle.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.walle.entity.Trabajador;
import com.walle.repository.TrabajadorRepository;
import com.walle.service.TrabajadorService;

public class TrabajadorServiceImp implements TrabajadorService {


	@Autowired
	TrabajadorRepository repo;
	@Override
	public List<Trabajador> listar() {
		return repo.findAll();
	}

	@Override
	public Optional<Trabajador> buscarId(int id) {
		return repo.findById(id);
	}

}
