package com.walle.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.walle.entity.Opcion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private String login;
    private String nombreCompleto;
    private int idUsuario;
    private Collection<? extends GrantedAuthority> authorities;
    private List<Opcion> opciones;

    public JwtDto(String token,String login, String nombreCompleto, int idUsuario, Collection<? extends GrantedAuthority> authorities,List<Opcion> lstOpciones) {
        this.token = token;
        this.login = login;
        this.nombreCompleto = nombreCompleto;
        this.authorities = authorities;
        this.idUsuario = idUsuario;
        this.opciones = lstOpciones;
    }

    
}
