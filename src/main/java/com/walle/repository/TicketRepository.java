package com.walle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.walle.entity.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
