package com.walle.repository;

import com.walle.dto.ByMonthDashboardDto;
import com.walle.dto.DashboardDto;
import com.walle.dto.GeneralStarsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.walle.entity.Ticket;
import com.walle.entity.Trabajador;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;



public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
    public List<Ticket> findAllByTrabajadorNombresContains(@Param("nombre") String nombres);
   
    @Query(value = "select * from ticket t join estado e on t.id_estado = e.id_estado where t.id_estado = ?1",
            nativeQuery = true)
    public List<Ticket> findAllByEstadoIdEstado(int id_estado);
    
    @Query(value = "select * from ticket t join estado e on t.id_estado = e.id_estado where t.estado = ?1 and t.id_trabajador = ?2",
            nativeQuery = true)
    public List<Ticket> listaDeTicketPorEstadoNombres(int estado, int trabajador);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update ticket set id_estado = ?1 where id_ticket = ?2",
			nativeQuery = true)
	public int actualizarPorEstado(int id_estado, int id_ticket);
	
    @Modifying
	@Query(value = "update ticket t set t.id_trabajador=?1 where id_ticket=?2",
			nativeQuery = true)
	public Ticket actualizarPorTrabajador(int id_trabajador, int id_ticket);
    //public Ticket actualizarPorTrabajador(int id_trabajador, int id_ticket);
	
	
	/*@Query(value = "select * from ticket",
			nativeQuery = true)
	public List<Ticket> findAllTicket();*/

	public List<Ticket> findAllByTrabajadorNombres(String nombres);

	@Query(value = "select * from ticket t join estado e on t.id_estado = e.id_estado where t.id_estado = ?1 and t.id_trabajador = ?2",
			nativeQuery = true)
	public List<Ticket> findAllByEstadoIdEstadoIdTrabajador(int id_estado, int id_trabajador);
	
	
	@Modifying
	@Query(value = "update ticket set estrellas = :estrella, opinion = :opinion  where id_ticket = :idTicket",
			nativeQuery = true)
	public void actualizarticketOpinionEstrella(int idTicket, int estrella, String opinion);


	@Modifying
	@Query(value = "update ticket set id_trabajador = :idtrabajador  where id_ticket = :id",
			nativeQuery = true)
	public void actualizartrabajadores(int idtrabajador, int id);

	
	@Query(value = "select * from trabajador where  id_trabajador = ?idtrabajador",
			nativeQuery = true)
	public List<Trabajador> listaDeTicketPorIdTrabajador(int idtrabajador);


	@Query(value = "select count(*) as cantidad, t.nombres, estrellas from ticket inner join trabajador as t where t.id_trabajador = ticket.id_trabajador group by estrellas, t.id_trabajador, t.nombres;",
	nativeQuery = true)
	public List<GeneralStarsDto> getGeneralStarDashboard();

	@Query(value = "select count(*) tickets, nombres from ticket t inner join trabajador tr on t.id_trabajador = tr.id_trabajador where t.fecha_creacion BETWEEN ?1 and ?2 group by t.id_trabajador, nombres;"
			, nativeQuery = true)
	public List<ByMonthDashboardDto> getByMonthDashboard(String startDate, String endDate);

	@Query(value = "select count(*) total,  count(if(ticket.id_estado = 1,id_estado, NULL)) noiniciado, count(if(id_estado=2,id_estado,null)) enproceso, count(if(ticket.id_estado = 3, id_estado, NULL)) finalizado  \n" +
			",count(if(ticket.id_estado = 4, id_estado, NULL)) cancelado from ticket;", nativeQuery = true)
	public DashboardDto getDashboard();
}
