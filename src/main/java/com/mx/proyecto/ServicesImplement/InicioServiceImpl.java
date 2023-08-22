package com.mx.proyecto.ServicesImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Repository.InicioRepository;
import com.mx.proyecto.Services.InicioService;

//VAMOS A MANEJAR TODA LA LOGICA DEL NEGOCIO QUE VENGAS DEL CONTROLADOR HACIA LA BASE DE DATOS
//O QUE VAYA DE LA BASE DE DATOS HACIE EL CONTROLADOR
@Service
public class InicioServiceImpl implements InicioService{

	@Autowired
	private InicioRepository inicioRepository;
	
	@Override
	public String saludoAmistoso() {
		System.out.println("LLEGANDO AL SERVICIO INICIO, ENTRANDO AL METODO SALUDOAMISTOSO");
		inicioRepository.saludoAmistoso();
		System.out.println("REGRESANDO AL SERVICIO INICIO");
		
		return null;
	}

}
