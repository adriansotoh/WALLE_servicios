package com.walle.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.walle.entity.Rol;
import com.walle.entity.Usuario;
import com.walle.security.UsuarioPrincipal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.apachecommons.CommonsLog;

@Getter
@Setter
@AllArgsConstructor
@CommonsLog
@ToString
public class UsuarioPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	private int idUsuario;
	private String correo;
	private String password;
	private String usuario;
	private static String nombreCompleto;
	private Collection<? extends GrantedAuthority> authorities;
	
	public static UsuarioPrincipal build(Usuario usuario, List<Rol> roles) {
		log.info(">>>UsuarioPrincipal >> " + usuario);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Rol x : roles) {
			authorities.add(new SimpleGrantedAuthority(x.getDescripcion()));
		}
		nombreCompleto = usuario.getNombreCompleto();
		return new UsuarioPrincipal(usuario.getId(), usuario.getCorreo(), usuario.getPassword(), usuario.getNombreCompleto(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return correo;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public int getUsuarioId() {
		return idUsuario;
	}

}
