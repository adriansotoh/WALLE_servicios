package com.walle.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walle.entity.Trabajador;
import com.walle.repository.TrabajadorRepository;
import com.walle.service.TrabajadorService;

@Service
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

	@Override
	public List<Trabajador> listarPorRol(int id) {
		return repo.listarPorIdRol(id);
	}
	
	

	@Override
	public Trabajador actualizaRegistraTrabajador(Trabajador trabajador) {

		return repo.save(trabajador);
	}

	@Override
	public List<Trabajador> listarPorGmail(String gmail) {
		// TODO Auto-generated method stub
		return repo.listarPorGmail(gmail);
	}
	
	

}
