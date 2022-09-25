package com.walle.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walle.dto.TicketDTO;
import com.walle.entity.Dificultad;
import com.walle.entity.Estado;
import com.walle.entity.Ticket;
import com.walle.entity.Trabajador;
import com.walle.entity.Urgencia;
import com.walle.repository.DificultadRepository;
import com.walle.repository.EstadoRepository;
import com.walle.repository.TicketRepository;
import com.walle.repository.TrabajadorRepository;
import com.walle.repository.UrgenciaRepository;
import com.walle.service.TicketService;

@Service
public class TicketServiceImp implements TicketService{
	
	@Autowired
	private TicketRepository repository;
	
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private TrabajadorRepository trabajadorRepo;
	@Autowired
	private UrgenciaRepository urgenciaRepo;
	@Autowired
	private DificultadRepository dificultadRepo;

	@Override
	public Ticket insertaTicket(TicketDTO obj) {
		Ticket salida = new Ticket();
		
		salida.setIdTicket(obj.getIdTicket());
		salida.setTitulo(obj.getTitulo());
		salida.setEquipo(obj.getEquipo());
		salida.setEstrellas(0);
		salida.setOpinion("--");
		salida.setDescripcion(obj.getDescripcion());
		
		Optional<Trabajador> oTrab = trabajadorRepo.findById(obj.getIdTrabajador());
		if (oTrab.isPresent()) {
			salida.setTrabajador(oTrab.get());
		}
		
		Optional<Estado> oEst = estadoRepo.findById(obj.getIdEstado());
		if (oEst.isPresent()) {
			salida.setEstado(oEst.get());
		}
		
		Optional<Urgencia> oUrg = urgenciaRepo.findById(obj.getIdUrgencia());
		if (oUrg.isPresent()) {
			salida.setUrgencia(oUrg.get());
		}
		
		Optional<Dificultad> oDif = dificultadRepo.findById(obj.getIdDificultad());
		if (oUrg.isPresent()) {
			salida.setDificultad(oDif.get());
		}
		
		return salida;
	}
	
	@Override
	public List<Ticket> listaTicket() {
		return repository.findAll();
	}

}
