package com.walle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walle.entity.Rol;
import com.walle.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("Select x from Usuario x where x.correo = :#{#usu.correo} and x.password = :#{#usu.password}")
	public abstract Usuario login(@Param(value = "usu") Usuario bean);
	
	public abstract Usuario findByCorreo(String correo);
	
	@Query("Select r from Rol r, Usuario u where r.idRol = u.idRol and u.id = :var_idUsuario")
	public abstract List<Rol> traerRolesDeUsuario(@Param("var_idUsuario")int idUsuario);
}
