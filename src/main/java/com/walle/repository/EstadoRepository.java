package com.walle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.walle.entity.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	
	@Query("select distinct x.descripcion from Estado x order by 1 asc")
	public abstract List<String> listaEstado();
}
