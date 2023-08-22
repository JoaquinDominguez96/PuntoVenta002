package com.mx.proyecto.Dto;

import java.math.BigDecimal;

public class Cursos {

	private BigDecimal cursoId;
	private String nombre_Curso;
	private Integer horas;
	private String especialidad;
	
	public BigDecimal getCursoId() {
		return cursoId;
	}
	public void setCursoId(BigDecimal cursoId) {
		this.cursoId = cursoId;
	}
	public String getNombre_Curso() {
		return nombre_Curso;
	}
	public void setNombre_Curso(String nombre_Curso) {
		this.nombre_Curso = nombre_Curso;
	}
	public Integer getHoras() {
		return horas;
	}
	public void setHoras(Integer horas) {
		this.horas = horas;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	
}
