package com.walle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.walle.entity.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
	@Query("select e from Estado e where (?1 is -1 or e.estado.id_Estado = ?1)")
	public abstract List<Ticket> listaTicket(int idEstado);
	
}
