package com.walle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walle.entity.Trabajador;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer>{
    @Query(value = "SELECT t FROM Trabajador t " +
            "INNER JOIN Rol r ON t.rol.idRol = r.idRol WHERE r.idRol = ?1")
    public List<Trabajador> listarPorIdRol(int idRol);
    
    @Query(value = "select * from Trabajador where t.correo = ?1",
            nativeQuery = true)
    public List<Trabajador> listarPorGmail(String gmail);
}
