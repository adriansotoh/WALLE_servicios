package com.walle.service;

import java.util.List;

import com.walle.entity.Ticket;

public interface TicketService {
	public List<Ticket> listaDeTicketPorNombres(String nombre);
}
