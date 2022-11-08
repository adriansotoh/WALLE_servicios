package com.walle.service;

import java.util.List;
import java.util.Optional;

import com.walle.dto.TicketDTO;
import com.walle.entity.Ticket;

public interface TicketService {

	public abstract Ticket insertaTicket(Ticket obj);	
	public abstract List<Ticket> lista();
	public List<Ticket> listaDeTicketPorNombres(String nombre);
	public List<Ticket> listaDeTicketPorEstado(int estado);
	public Optional<Ticket> listaDeTicketPorId(int id);
	public Ticket actualizarTicket(Ticket ticket);
	public Ticket actualizarTicketPorEstado(int estado, int id);
	public abstract void actualizarTicketPorTrabajador(int trabajador, int id);
	//public Ticket actualizarTicketPorTrabajador(int trabajador, int id);
	
	
	
}
