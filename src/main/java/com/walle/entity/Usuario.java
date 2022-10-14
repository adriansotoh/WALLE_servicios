package com.walle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trabajador")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trabajador")
	private int id;
	
	@Column(name = "id_rol")
	private int idRol;
	
	private String documento;
	private String nombres;
	private String apellidos;
	private String correo;
	private String descripcion;
	private String usuario;
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false, insertable = false, updatable = false)
	private Rol rol;
	
	public Usuario() {}
	
	public Usuario(int id, int idRol, String documento, String nombres, String apellidos, String correo,
			String descripcion, String usuario, String password, Rol rol) {
		this.id = id;
		this.idRol = idRol;
		this.documento = documento;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
	}

	public String getNombreCompleto() {
		return nombres.concat(" ").concat(apellidos);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
}
