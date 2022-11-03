package com.walle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.walle.entity.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Integer>{
    public List<Ticket> findAllByTrabajadorNombresContains(@Param("nombre") String nombres);
   
    @Query(value = "select * from ticket t join estado e on t.id_estado = e.id_estado where t.id_estado = ?1",
            nativeQuery = true)
    public List<Ticket> findAllByEstadoIdEstado(int id_estado);
	
    @Query(value = "update ticket set id_estado = ?1 where id_ticket = ?2",
			nativeQuery = true)
	public Ticket actualizarPorEstado(int id_estado, int id_ticket);
	
	@Query(value = "update ticket set id_trabajador = ?1 where id_ticket = ?2",
			nativeQuery = true)
	public Ticket actualizarPorTrabajador(int id_trabajador, int id_ticket);

}
