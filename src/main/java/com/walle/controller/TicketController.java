package com.walle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.walle.entity.Ticket;
import com.walle.service.TicketService;

@RestController
@RequestMapping("/url/ticket")
public class TicketController {
	
	@Autowired
	private TicketService serv;
	
	@GetMapping("{nombres}")
	@ResponseBody
	public ResponseEntity<List<Ticket>> lista(@PathVariable String nombres){
		List<Ticket> lista = serv.listaDeTicketPorNombres(nombres);
		return ResponseEntity.ok(lista);
	}
}
