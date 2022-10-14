package com.walle.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.walle.entity.Rol;
import com.walle.entity.Usuario;
import com.walle.security.UsuarioPrincipal;
import com.walle.repository.UsuarioRepository;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class UsuarioSeguridadServiceImp implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info(">>>loadUserByUsername >>> " + username);
		UserDetails userDetails = null;
		try {
			Usuario objUsuario = usuarioRepository.findByCorreo(username);
			if (objUsuario != null) {
				log.info("========|||=========== " + objUsuario.getCorreo());

				List<Rol> lstRol = usuarioRepository.traerRolesDeUsuario(objUsuario.getId());
				log.info("========|||=========== " + lstRol);

				userDetails = UsuarioPrincipal.build(objUsuario, lstRol);
			}
		} catch (IndexOutOfBoundsException e) {
			throw new UsernameNotFoundException("Wrong username");
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Database Error");
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Unknown Error");
		}
		return userDetails;
	}

}
