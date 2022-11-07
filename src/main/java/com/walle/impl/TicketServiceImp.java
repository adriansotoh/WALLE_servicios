package com.walle.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walle.entity.Dificultad;
import com.walle.entity.Estado;
import com.walle.entity.Ticket;
import com.walle.entity.Trabajador;
import com.walle.entity.Urgencia;
import com.walle.repository.DificultadRepository;
import com.walle.repository.EstadoRepository;
import com.walle.repository.TicketRepository;
import com.walle.repository.TrabajadorRepository;
import com.walle.repository.UrgenciaRepository;
import com.walle.service.TicketService;

@Service
public class TicketServiceImp implements TicketService{
	
	@Autowired
	private TicketRepository repository;
	
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private TrabajadorRepository trabajadorRepo;
	@Autowired
	private UrgenciaRepository urgenciaRepo;
	@Autowired
	private DificultadRepository dificultadRepo;

	@Override
	public Ticket insertaTicket(Ticket obj) {
		
		
		
		return repository.save(obj);
	}
	
	@Override
	public List<Ticket> listaTicket() {
		return repository.findAll();
	}

	@Override
	public List<Ticket> listaDeTicketPorNombres(String nombres) {
		return repository.findAllByTrabajadorNombresContains(nombres);
	}
	@Override
	public Ticket actualizarTicket(Ticket ticket) {
		return repository.save(ticket);
	}

	@Override
	public Ticket actualizarTicketPorEstado(int estado, int id) {
		return repository.actualizarPorEstado(estado, id);
	}


	@Override
	public List<Ticket> listaDeTicketPorEstado(int estado) {
		return repository.findAllByEstadoIdEstado(estado);
	}

	@Override
	public List<Ticket> listaDeTicketPorEstadoNombres(int estado, int trabajador) {
		return null;
	}

	@Override
	public Optional<Ticket> listaDeTicketPorId(int id) {
		return repository.findById(id);
	}

}
