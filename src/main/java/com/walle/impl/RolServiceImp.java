package com.walle.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walle.entity.Rol;
import com.walle.repository.RolRepository;
import com.walle.service.RolService;

@Service
public class RolServiceImp implements RolService {

	@Autowired
	RolRepository repo;
	
	@Override
	public List<Rol> listar() {
		return repo.findAll(); 
	}

	@Override
	public Optional<Rol> buscarId(int id) {
		return repo.findById(id);
	}
	
}
