package com.mx.proyecto.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Services.InicioService;

//ESTE CONTROLADOR Y OTROS MAS VAN A ADMINISTRAR LAS PETICIONES QUE EL USUARIO HAGA A LA APLICACION

@Controller
@RequestMapping("inicio")
public class InicioController {

	@Autowired
	private InicioService inicioService;
	
	@RequestMapping(value ="/irAInicio", method = RequestMethod.POST)
	public String irAInicio() {
		
		return "Inicio";
	}
	
	@RequestMapping(value = "/getAlumnos", method = RequestMethod.GET)
	public String getAlumnos() {
		System.out.println("LLEGANDO AL CONTROLADOR INICIO, ENTRANDO AL METODO GET ALUMNO");
		inicioService.saludoAmistoso();
		System.out.println("REGRESANDO AL CONTROLADOR INICIO");
		return "Inicio";
	}
	
}
