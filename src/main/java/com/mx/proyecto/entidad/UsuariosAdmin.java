package com.mx.proyecto.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//select * from ESQUEMA.USUARIO_ADMIN; -- JDBTEMPLATE
// read = (select * from) + ESQUEMA.USUARIO_ADMIN =>> select * from ESQUEMA.USUARIO_ADMIN; -- HIBERNATE

//1.- Una entidad/entity es una referencia a las tablas de la base de datos oracle/Mysql/Postgress


@Entity // Anotacion para indicarle que esta clase va a ser una entity/entidad
@Table(name = "USUARIOS_ADMIN", schema = "JOAQUIN") // Anotacion table donde indicamos el nombre de la tabla y el esquema
public class UsuariosAdmin {
	
	@Id // Es para indicarle que el campo/atributo va ser un id
	@Column(name = "ID_USER", unique = true, nullable = false, precision = 11) // Es para indicar que es una columna de la tabla y se pueden agregar propiedades
	private Long idUser;
	
	@Column(name = "NOMBRE_COMPLETO", length = 100)
	private String nombreCompleto;
	
	@Column(name = "EDAD")
	private int edad;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "ESTADO")
	private int estado;
	
	@Column(name = "ROL")
	private int rol;
	
	
	
	

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}
	

}// fin de la clase