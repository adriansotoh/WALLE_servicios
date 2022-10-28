package com.walle.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walle.entity.Dificultad;
import com.walle.repository.DificultadRepository;
import com.walle.service.DificultadService;

@Service
public class DificultadServiceImp implements DificultadService {

	@Autowired
	DificultadRepository repo;
	
	@Override
	public List<Dificultad> listar() {
		return repo.findAll();
	}

	@Override
	public Optional<Dificultad> buscarId(int id) {
		return repo.findById(id);
	}

}
