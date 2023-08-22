package com.mx.proyecto.RepositoryImplement;

import org.springframework.stereotype.Repository;

import com.mx.proyecto.Repository.InicioRepository;

//@REPOSITORY VA A CONVERTIR A ESTA CLASE EN UN REPOSITORIO PARA CONSULTAS A BASE DE DATOS

@Repository
public class InicioRepositoryImpl implements InicioRepository{

	@Override
	public String saludoAmistoso() {

		System.out.println("LLEGANDO AL REPOSITORIO DE INICIO, ENTRANDO AL METODO SALUDOAMISTOSO");
		
		return null;
	}

}
