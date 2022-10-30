package com.walle.service;

import java.util.List;
import java.util.Optional;

import com.walle.entity.Ticket;

public interface TicketService {
	public List<Ticket> listaDeTicketPorNombres(String nombre);
	public List<Ticket> listaDeTicketPorEstado(int estado);
	public List<Ticket> listaDeTicketPorEstadoNombres(int estado, int trabajador);
	public Optional<Ticket> listaDeTicketPorId(int id);
	public Ticket actualizarTicket(Ticket ticket);
	public Ticket actualizarTicketPorEstado(int estado, int id);
}
