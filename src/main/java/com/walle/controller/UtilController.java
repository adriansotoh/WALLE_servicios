package com.walle.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.walle.entity.Dificultad;
import com.walle.entity.Estado;
import com.walle.entity.Urgencia;
import com.walle.entity.Trabajador;

import com.walle.service.DificultadService;
import com.walle.service.EstadoService;
import com.walle.service.UrgenciaService;
import com.walle.service.TrabajadorService;
import com.walle.utils.AppSettings;




@RestController
@RequestMapping("/util")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class UtilController {

	@Autowired
	private DificultadService dificultadService;

	@Autowired
	private TrabajadorService trabajadorService;

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private UrgenciaService urgenciaService;
	
	
	
	@GetMapping("/listaTrabajador")
	@ResponseBody
	public List<Trabajador> listaPais() {
		return trabajadorService.listar();
		
	}

	@GetMapping("/listaEstado")
	@ResponseBody
	public List<Estado> listaCategoria() {
		return estadoService.listar();
	}
	
	@GetMapping("/listaUrgencia")
	@ResponseBody
	public List<Urgencia> listaGrado() {
		return urgenciaService.listar();
	}

	@GetMapping("/listaDificultad")
	@ResponseBody
	public List<Dificultad> listaModalidad() {
		return dificultadService.listar();
				
	}
	
	
}
