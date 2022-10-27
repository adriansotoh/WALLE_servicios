package com.walle.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.walle.dto.TicketDTO;
import com.walle.entity.Ticket;
import com.walle.service.TicketService;
import com.walle.utils.AppSettings;


@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> inserta(@Valid @RequestBody TicketDTO obj, Errors errors){
		
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

		Ticket objSalida = ticketService.insertaTicket(obj);
		if (objSalida == null) {
			lstMensajes.add("Error en el registro");
		}else {
			lstMensajes.add("Se registró el ticket con el ID ==> " + objSalida.getIdTicket());
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@GetMapping
	@ResponseBody
	public  ResponseEntity<List<Ticket>> listaTicket() {
		List<Ticket> lista = ticketService.listaTicket();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/porFiltro")
	@ResponseBody
	public ResponseEntity<List<Ticket>> listaTicketPorFiltro(
				@RequestParam(name = "idEstado" , required = false, defaultValue = "-1" ) int idEstado) {
		
		List<Ticket> lista = ticketService.listaTicket(idEstado);	
		

		return ResponseEntity.ok(lista);
	}

}
