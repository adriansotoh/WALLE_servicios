package com.walle.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walle.dto.TicketDTO;
import com.walle.entity.Ticket;
import com.walle.repository.TicketRepository;
import com.walle.service.TicketService;

@Service
public class TicketServiceImp implements TicketService{
	
	@Autowired
	private TicketRepository repository;

	@Override
	public Ticket insertaTicket(TicketDTO obj) {
		Ticket salida = new Ticket();
		
		return salida;
	}
	
	@Override
	public List<Ticket> listaTicket() {
		return repository.findAll();
	}

}
