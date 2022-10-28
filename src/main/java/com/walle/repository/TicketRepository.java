package com.walle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.walle.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	public List<Ticket> findAllByTrabajadorNombres(String nombres);
	@Query(value = "select * from ticket t join estado e on t.id_estado = e.id_estado where t.id_estado = ?1",
			nativeQuery = true)
	public List<Ticket> findAllByEstadoIdEstado(int id_estado);
	@Query(value = "update ticket set id_estado = ?1 where id_ticket = ?2",
			nativeQuery = true)
	public Ticket actualizarPorEstado(int id_estado, int id_ticket);
}
