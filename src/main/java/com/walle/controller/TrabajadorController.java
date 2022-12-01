package com.walle.controller;

import com.walle.entity.Trabajador;
import com.walle.impl.TrabajadorServiceImp;
import com.walle.utils.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trabajadores")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TrabajadorController {

    @Autowired
    TrabajadorServiceImp serviceTrabajador;

	@GetMapping("/listarTodos")
	public List<Trabajador> listarTodos() {

		return serviceTrabajador.listar();
	}
	


	
	@PutMapping("/actualizarTrabajador")
	public ResponseEntity<Trabajador> actualizarTrabajador(@RequestBody Trabajador trab) throws Exception{
	
//		Trabajador bean=serviceTrabajador.buscarId(trab.getId_trabajador());
		
		Optional<Trabajador> idTrabajador = serviceTrabajador.buscarId(trab.getId_trabajador());

		if(idTrabajador==null) {
			throw new NotFoundException();
		}
		
		else {
			Trabajador tra = serviceTrabajador.actualizaRegistraTrabajador(trab);
		}

		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/registrarTrabajador")
	public ResponseEntity<Trabajador> registrarMed(@RequestBody Trabajador trab) throws Exception{
		
		String contra = trab.getPassword();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(contra);
		trab.setPassword(encodedPassword);
		
//		List<Trabajador> listTrabajador = serviceTrabajador.listarPorGmail(trab.getCorreo());
		
//		Trabajador m = new Trabajador();
		
//		if(listTrabajador.size() != 0) {
		Trabajador	 m=serviceTrabajador.actualizaRegistraTrabajador(trab);
//		}else {
	
//			throw new NotFoundException();
//		}
		
	
		return new ResponseEntity<>(m,HttpStatus.CREATED);
	}
	
}
