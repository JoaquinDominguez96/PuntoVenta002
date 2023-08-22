package com.mx.proyecto.Dto;

import java.math.BigDecimal;

public class Maestros {

	private BigDecimal MaestroId;
	private String nombre_maestro;
	private String correo;
	private Long telefono;
	
	
	public BigDecimal getMaestroId() {
		return MaestroId;
	}
	public void setMaestroId(BigDecimal maestroId) {
		MaestroId = maestroId;
	}
	public String getNombre_maestro() {
		return nombre_maestro;
	}
	public void setNombre_maestro(String nombre_maestro) {
		this.nombre_maestro = nombre_maestro;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Long getTelefono() {
		return telefono;
	}
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}




}
