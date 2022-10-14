package com.walle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("porNombres/{nombres}")
	@ResponseBody
	public ResponseEntity<List<Ticket>> lista(@PathVariable String nombres){
		List<Ticket> lista = serv.listaDeTicketPorNombres(nombres);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("porEstado/{estado}")
	@ResponseBody
	public ResponseEntity<List<Ticket>> listaPorEstado(@PathVariable int estado){
		List<Ticket> lista = serv.listaDeTicketPorEstado(estado);
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<?> actualizarTicket(@RequestBody Ticket ticket){
		HashMap<String, Object> result = new HashMap<String, Object>();
		Optional<Ticket> idTicket = serv.listaDeTicketPorId(ticket.getId_ticket());
		
		if(idTicket.isPresent()) {
			Ticket tckt = serv.actualizarTicket(ticket);
			if(tckt == null) {
				result.put("mensaje", "Error al actualizar");
			}
			else {
				result.put("mensaje", "Se actualizó el ticket " + tckt.getId_ticket() + " correctamente");
			}
		} else {
			result.put("mensaje", "No existe el ticket " + ticket.getId_ticket());
		}
		
		return ResponseEntity.ok(result);
	}
}
