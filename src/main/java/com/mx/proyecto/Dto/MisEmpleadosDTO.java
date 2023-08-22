package com.mx.proyecto.Dto;

public class MisEmpleadosDTO {
	
	private Long idMiEmpleado;
	private String nombreCompleto;
	private String rfc;
	private String curp;
	private int edad;
	private char sexo;
	private String direccion;
	private long nss;
	private long telefono;
	private int activo;
	
	
	public Long getIdMiEmpleado() {
		return idMiEmpleado;
	}
	public void setIdMiEmpleado(Long idMiEmpleado) {
		this.idMiEmpleado = idMiEmpleado;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public long getNss() {
		return nss;
	}
	public void setNss(long nss) {
		this.nss = nss;
	}
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	

}
