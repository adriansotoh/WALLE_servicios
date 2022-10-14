package com.walle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walle.entity.Ticket;
import com.walle.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository repo;
	
	@Override
	public List<Ticket> listaDeTicketPorNombres(String nombres) {
		return repo.findAllByTrabajadorNombres(nombres);
	}

	@Override
	public Ticket actualizarTicket(Ticket ticket) {
		return repo.save(ticket);
	}

	@Override
	public List<Ticket> listaDeTicketPorEstado(int estado) {
		return repo.findAllByEstadoIdEstado(estado);
	}

	@Override
	public Optional<Ticket> listaDeTicketPorId(int id) {
		return repo.findById(id);
	}

}
