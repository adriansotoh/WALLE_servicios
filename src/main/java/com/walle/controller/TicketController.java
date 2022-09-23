package com.walle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.walle.entity.Ticket;
import com.walle.service.TicketService;

@RestController
@RequestMapping("/url/ticket")
public class TicketController {
	
	@Autowired
	public TicketService ticketService;
	
	@GetMapping
	@ResponseBody
	public  ResponseEntity<List<Ticket>> listaTicket() {
		List<Ticket> lista = ticketService.listaTicket();
		return ResponseEntity.ok(lista);
	}

}
