package com.walle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.walle.entity.Ticket;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
	//@Query("select e from Estado e where (?1 is -1 or e.estado.id_Estado = ?1)")
	//public abstract List<Ticket> listaTicket(int idEstado);
	

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
    public List<Ticket> findAllByTrabajadorNombres(String nombres);
    
    @Query(value = "select * from ticket t join estado e on t.id_estado = e.id_estado where t.id_estado = ?1",
            nativeQuery = true)
    public List<Ticket> findAllByEstadoIdEstado(int id_estado);

}
