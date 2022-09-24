package com.walle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walle.entity.Ticket;
import com.walle.repository.TicketRepository;

@Service
public class TicketServiceImp implements TicketService{
	
	@Autowired
	private TicketRepository repository;

	@Override
	public Ticket insertaTicket(Ticket obj) {
		
		return repository.save(obj);
	}
	
	
}
