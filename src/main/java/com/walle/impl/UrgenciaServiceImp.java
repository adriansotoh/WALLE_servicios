package com.walle.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walle.entity.Urgencia;
import com.walle.repository.UrgenciaRepository;
import com.walle.service.UrgenciaService;

@Service
public class UrgenciaServiceImp implements UrgenciaService {

	@Autowired
	UrgenciaRepository repo;
	
	@Override
	public List<Urgencia> listar() {
		return repo.findAll(); 
	}

	@Override
	public Optional<Urgencia> buscarId(int id) {
		return repo.findById(id);
	}
	
}
