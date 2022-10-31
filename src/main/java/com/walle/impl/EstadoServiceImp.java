package com.walle.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walle.entity.Estado;
import com.walle.repository.EstadoRepository;
import com.walle.service.EstadoService;

@Service
public class EstadoServiceImp implements EstadoService {

	@Autowired
	EstadoRepository repo;
	
	@Override
	public List<Estado> listar() {
		return repo.findAll(); 
	}

	@Override
	public Optional<Estado> buscarId(int id) {
		return repo.findById(id);
	}

	
}
