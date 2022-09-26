package com.walle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walle.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	public List<Ticket> findAllByTrabajadorNombres(String nombres);
}
