package com.walle.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.walle.impl.TicketServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import org.springframework.transaction.annotation.Transactional;

import com.walle.utils.AppSettings;
import com.walle.entity.Ticket;
import com.walle.impl.TrabajadorServiceImp;
import com.walle.service.TicketService;


@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
    private JavaMailSender mailSender;
	
	
	@GetMapping("/enviaremail")
	public void sendEmail() {

            SimpleMailMessage email = new SimpleMailMessage();

            //recorremos la lista y enviamos a cada cliente el mismo correo
            email.setTo("christiangallegos2015@gmail.com");
            email.setSubject("prueba06112022");
            email.setText("prueba");
         
            mailSender.send(email);
    
    }

	
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> inserta(@Valid @RequestBody Ticket obj, Errors errors){
		
		HashMap<String, Object> salida = new HashMap<>();
		List<String> lstMensajes = new ArrayList<String>();
		salida.put("errores", lstMensajes);
		
		List<ObjectError> lstErrors =  errors.getAllErrors();
		for (ObjectError objectError : lstErrors) {
			objectError.getDefaultMessage();
			lstMensajes.add(objectError.getDefaultMessage());
		}

		if (!CollectionUtils.isEmpty(lstMensajes)) {
			return ResponseEntity.ok(salida);
		}
		
		obj.setEstrellas(1);
		obj.setOpinion("");

		Ticket objSalida = ticketService.insertaTicket(obj);
		if (objSalida == null) {
			lstMensajes.add("Error en el registro");
		}else {
			lstMensajes.add("Se registró el ticket con el ID ==> " + objSalida.getIdTicket());
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@GetMapping("/listarTickets")
	@ResponseBody
	public  List<Ticket> listaTicket() {
		return ticketService.lista();
	}

	@GetMapping("/listarPorNombres/{nombres}")
	@ResponseBody
	public ResponseEntity<List<Ticket>> listaPorNombre(@PathVariable String nombres){
		List<Ticket> lista = ticketService.listaDeTicketPorNombres(nombres);
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/listarPorEstado/{estado}")
	@ResponseBody
	public ResponseEntity<List<Ticket>> listaPorEstado(@PathVariable int estado){
		List<Ticket> lista = ticketService.listaDeTicketPorEstado(estado);
		return ResponseEntity.ok(lista);
	}

	
	@GetMapping("/buscarPorId/{id}")
	@ResponseBody
	public ResponseEntity<Optional<Ticket>> buscarPorId(@PathVariable int id){
		Optional<Ticket> ticket = ticketService.listaDeTicketPorId(id);
		return ResponseEntity.ok(ticket);
	}

	@PutMapping
	@ResponseBody
	public ResponseEntity<?> actualizarTicket(@RequestBody Ticket ticket){
		HashMap<String, Object> result = new HashMap<String, Object>();
		Optional<Ticket> idTicket = ticketService.listaDeTicketPorId(ticket.getIdTicket());

		if(idTicket.isPresent()) {
			Ticket tckt = ticketService.actualizarTicket(ticket);
			if(tckt == null) {
				result.put("mensaje", "Error al actualizar");
			}
			else {
				result.put("mensaje", "Se actualizó el ticket " + tckt.getIdTicket() + " correctamente");
			}
		} else {
			result.put("mensaje", "No existe el ticket " + ticket.getIdTicket());
		}
		
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/actualizarEstado")
	@ResponseBody
	public ResponseEntity<?> actualizarTicketPorEstado(@PathVariable int id_estado, @PathVariable int id_ticket){
		HashMap<String, Object> result = new HashMap<String, Object>();
		Optional<Ticket> idTicket = ticketService.listaDeTicketPorId(id_ticket);
		
		if(idTicket.isPresent()) {
			Ticket tckt = ticketService.actualizarTicketPorEstado(id_estado, id_ticket);
			if(tckt == null) {
				result.put("mensaje", "Error al actualizar");
			}
			else {
				result.put("mensaje", "Se actualizó el estado de ticket " + tckt.getIdTicket() + " correctamente");
			}
		} else {
			result.put("mensaje", "No existe el ticket " + id_ticket);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/actualizarTrabajador")
	@ResponseBody
	public ResponseEntity<?> actualizarTicketPorTrabajador(@PathParam("id_trabajador") int id_trabajador, @PathParam("id_ticket") int id_ticket){
		HashMap<String, Object> result = new HashMap<String, Object>();
		System.out.println("si lee :" + id_trabajador + "/" + id_ticket);
		Optional<Ticket> idTicket = ticketService.listaDeTicketPorId(id_ticket);
		
		if(idTicket.isPresent()) {
			 Integer tckt=1;
			 ticketService.actualizarTicketPorTrabajador(id_trabajador, id_ticket);
			if(tckt == null) {
				result.put("mensaje", "Error al actualizar al encargado");
			}
			else {
				result.put("mensaje", "Se actualizó al encargado del ticket " + " correctamente");
			}
		} else {
			result.put("mensaje", "No existe el ticket " + id_ticket);
		}
		
		/*if(idTicket.isPresent()) {
			 Integer tckt = 1;
					 ticketService.actualizarTicketPorTrabajador(id_trabajador, id_ticket);
			if(tckt == null) {
				result.put("mensaje", "Error al actualizar al encargado");
			}
			else {
				result.put("mensaje", "Se actualizó al encargado del ticket " + " correctamente");
			}
		} else {
			result.put("mensaje", "No existe el ticket " + id_ticket);
		}*/
		System.out.println("si leeeeeeeeeeeeeeeee"+ id_trabajador);
		System.out.println("si leeeeeeeeeeeeeeeee"+ id_ticket);
		return ResponseEntity.ok(result);
	}

	@GetMapping("actualizarOpinionEstrella/{ticket}/{estrella}/{opinio}")
	@ResponseBody
	@Transactional
	public ResponseEntity<?> actualizarticketOpinionEstrella(@PathVariable int ticket, @PathVariable int estrella, @PathVariable String opinio){
		HashMap<String, Object> result = new HashMap<String, Object>();
		Optional<Ticket> idTicket = ticketService.listaDeTicketPorId(ticket);
		
		if(idTicket.isPresent()) {
			ticketService.actualizarticketOpinionEstrella(ticket, estrella, opinio);
	
		
				result.put("mensaje", "Se actualizó la estrella y opinion de ticket " + ticket + " correctamente");
			
		} else {
			result.put("mensaje", "No existe el ticket " + ticket);
		}
		
		return ResponseEntity.ok(result);
	}

}
