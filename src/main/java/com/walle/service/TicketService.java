package com.walle.service;

import java.util.List;

import com.walle.dto.TicketDTO;
import com.walle.entity.Ticket;

public interface TicketService {

	public abstract Ticket insertaTicket(TicketDTO obj);	
	public abstract List<Ticket> listaTicket();

}
