package com.walle.service;

import java.util.List;

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

}
