package com.walle.controller;


import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.walle.impl.TicketServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

import com.walle.entity.Trabajador;
import com.walle.impl.TrabajadorServiceImp;
import com.walle.entity.Estado;
import com.walle.impl.EstadoServiceImp;
import com.walle.impl.TicketServiceImp;
import com.walle.service.TicketService;

import com.walle.service.TrabajadorService;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;


@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TicketController {

	@Autowired
	private TicketService ticketService;
	@Autowired
	private EstadoServiceImp estadoService;
	@Autowired
	private TrabajadorService TrabajadorService;
	
	@Autowired
    private JavaMailSender mailSender;
	
	
	@GetMapping("/enviaremail")
	public void sendEmail() {
	
           try {
        	   MimeMessage mimeMessage = mailSender.createMimeMessage();
               MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
               String htmlMsg = "<a href='https://www.whatsapp.com/' ><img src='https://www.openmet.com/wp-content/uploads/2022/04/encuestas-pulso.png'/></a>";
               mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
               //helper.setText(htmlMsg, true); // Use this or above line.
               helper.setTo("adriansotohidalgo16@gmail.com");
               helper.setSubject("CGDAAAA");
               helper.setFrom("adriansotohidalgo16@gmail.com");
               mailSender.send(mimeMessage);
           }catch(Exception ex) {
        	   System.out.println(ex);
           }     
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
		obj.setIdUsuario(1);
		obj.setIdTrabajador(6);

		Ticket objSalida = ticketService.insertaTicket(obj);
		if (objSalida == null) {
			lstMensajes.add("Error en el registro");
		}else {
			
			 try {
	        	   MimeMessage mimeMessage = mailSender.createMimeMessage();
	               MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	               String parte1 = objSalida.getIdTicket() + " '>aqui</a> </p>";
	               String prueba = "<p>Se ha registrado un ticket, Puede dar seguimiento a su ticket dando click <a href='http://localhost:4200/detalleTicket/" + parte1;
	               String htmlMsg = prueba;
	               mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
//	               helper.setText("Su ticket se ha registrado "); // Use this or above line.
	               helper.setTo(obj.getCorreo_cliente());
	               helper.setSubject("Ticket Registrado");
	               helper.setFrom(obj.getCorreo_cliente());
	               mailSender.send(mimeMessage);
	           }catch(Exception ex) {
	        	   System.out.println(ex);
	           }
			
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
	
	@GetMapping("/listarPorEstadoTrabajador/{estado}/{trabajador}")
	@ResponseBody
	public ResponseEntity<List<Ticket>> listaPorEstado(@PathVariable int estado, @PathVariable int trabajador){
		List<Ticket> lista = ticketService.listaDeTicketPorEstadoNombres(estado, trabajador);
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


	@PutMapping("/actualizarEstado/{id_estado}/{id_ticket}")
	@ResponseBody
	public ResponseEntity<?> actualizarTicketPorEstado(@PathVariable int id_estado, @PathVariable int id_ticket){
		HashMap<String, Object> result = new HashMap<String, Object>();
		Optional<Ticket> idTicket = ticketService.listaDeTicketPorId(id_ticket);
		Optional<Estado> idEstado = estadoService.buscarId(id_estado);
		
		if(idTicket.isPresent() && idEstado.isPresent()) {
			int tckt = ticketService.actualizarTicketPorEstado(id_estado, id_ticket);
			if(tckt == -1) {
				result.put("mensaje", "Error al actualizar");
			}
			else {
			
				if(id_estado == 3 ) {
					
					 try {
			        	   MimeMessage mimeMessage = mailSender.createMimeMessage();
			               MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	
			               String prueba = "<p>Se ha registrado un ticket, Puede dar seguimiento a su ticket dando click <a href='http://localhost:4200/feedback?id=" + idTicket.get().getIdTicket() + "'" ;
			               String htmlMsg = prueba;
			               mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
//			               helper.setText("Su ticket se ha registrado "); // Use this or above line.
			               helper.setTo(idTicket.get().getCorreo_cliente());
			               helper.setSubject("Su ticket ha sido finalizado");
			               helper.setFrom(idTicket.get().getCorreo_cliente() );
			               mailSender.send(mimeMessage);
			           }catch(Exception ex) {
			        	   System.out.println(ex);
			           }
					
					
				
				}else if(id_estado == 4) {
					
					 try {
			        	   MimeMessage mimeMessage = mailSender.createMimeMessage();
			               MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	
			               String prueba = "<p>Se ha cancelado su ticket";
			               String htmlMsg = prueba;
			               mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
//			               helper.setText("Su ticket se ha registrado "); // Use this or above line.
			               helper.setTo(idTicket.get().getCorreo_cliente());
			               helper.setSubject("Su ticket ha sido cancelado");
			               helper.setFrom(idTicket.get().getCorreo_cliente() );
			               mailSender.send(mimeMessage);
			           }catch(Exception ex) {
			        	   System.out.println(ex);
			           }
					

				}
				result.put("mensaje", "Se actualizó el estado de ticket " + id_ticket + " correctamente");
			}
		} else {
			if(idTicket.isEmpty()) {
				result.put("mensaje", "No existe el ticket " + id_ticket);
			}
			else if(idEstado.isEmpty()) {
				result.put("mensaje", "No existe el estado " + id_estado);
			}
		}
		
		return ResponseEntity.ok(result);
	}
	
//	@Transactional
	@PutMapping("/actualizarTrabajador")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizarTicketPorTrabajador(
			@RequestParam(name = "id_trabajador", required = true, defaultValue = "-1" )int id_trabajador,
			@RequestParam(name = "id_ticket", required = true, defaultValue = "-1" ) int id_ticket){
		HashMap<String, Object> result = new HashMap<String, Object>();
//		Optional<Ticket> idTicket = ticketService.listaDeTicketPorId(id_ticket);
		
//		if(idTicket.isPresent()) {
//			 ticketService.actualizarTicketPorTrabajador(id_trabajador, id_ticket);
//			 sendEmail();
//		} else {
//			result.put("mensaje", "No existe el ticket " + id_ticket);
//		}
		try {
			Ticket t =  ticketService.actualizarTicketPorTrabajador(id_trabajador, id_ticket);
			if (t == null) {
				result.put("mensaje", "Ocurrio un error al actualizar el trabajador");
				result.put("ticket", t);
			} else {
				result.put("mensaje", "Actualizacion de trabajador exitosa");
				result.put("ticket", null);
			}
		} catch(Exception e) {
			result.put("mensaje", "Ocurrio un error al actualizar el trabajador");
			result.put("ticket", null);
		}
		return ResponseEntity.ok(result);
	}

	// TODO CMABIAR METODO DE RECEPCION DE PARAMETROS
	@PostMapping("actualizarOpinionEstrella")
	@ResponseBody
	@Transactional
	public ResponseEntity<?> actualizarticketOpinionEstrella(
			@RequestParam(name = "id") int ticket,
			@RequestParam(name = "star") int estrella,
			@RequestParam(name = "opinion") String opinio){
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
	
	@GetMapping("actualizarTrabajadores/{id_trabajador}/{idticket}")
	@ResponseBody
	@Transactional
	public ResponseEntity<?> asignarTicketATrabajador(@PathVariable int id_trabajador, @PathVariable int idticket){
		HashMap<String, Object> result = new HashMap<String, Object>();
		Optional<Ticket> idTicket = ticketService.listaDeTicketPorId(idticket);
		
		Optional<Trabajador> trabajador = TrabajadorService.buscarId(id_trabajador);

		if(idTicket.isPresent()) {
			ticketService.actualizartrabajadores(id_trabajador, idticket);
			
			//correo
			
			 try {
	        	   MimeMessage mimeMessage = mailSender.createMimeMessage();
	               MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//	               String htmlMsg = "<a href='https://www.whatsapp.com/' ><img src='https://www.openmet.com/wp-content/uploads/2022/04/encuestas-pulso.png'/></a>";
//	               mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
	               helper.setText("tienes un ticket a cargo"); // Use this or above line.
	               helper.setTo(trabajador.get().getCorreo());
	               helper.setSubject("prueba");
	               helper.setFrom(trabajador.get().getCorreo());
	               mailSender.send(mimeMessage);
	           }catch(Exception ex) {
	        	   System.out.println(ex);
	           }
			
	
		
				result.put("mensaje", "Se actualizó el trabajador correctamente");
			
		} else {
			result.put("mensaje", "No existe el ticket " + idticket);
		}
		
		return ResponseEntity.ok(result);
	}
	
	
	@DeleteMapping("/eliminaTicket/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaDocente(@PathVariable("id") int idTicket) {
		Map<String, Object> salida = new HashMap<>();
		try {
			ticketService.eliminaTicket(idTicket);
			salida.put("mensaje", "Se elimino el ticket");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se pudo eliminar el ticket");
		}
		return ResponseEntity.ok(salida);
	}

}
