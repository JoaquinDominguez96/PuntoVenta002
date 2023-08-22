package com.mx.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mx.proyecto.Dto.MisEmpleadosDTO;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Services.MisEmpleadosService;


@Controller
@RequestMapping("misEmpleados")
public class MisEmpleadosController {
	
	@Autowired
	public MisEmpleadosService misEmpleadosService;
	
	// CRUD = registrar, actualizarm consulta y eliminar
	
	// json => un conjunto de atributos - valor -> nombre: isaul
	
	
	// lista de usuarios -> select * from tabla;
		@ResponseBody
		@RequestMapping(value="/getMisEmpleados", method= RequestMethod.GET, produces = "application/json")
		public ResponseDto getMisEmpleados() {
			
			return misEmpleadosService.getEmpleados();
		}
	
	
		
		
		
		// Servicio para inserta un nuevo usuario
		@ResponseBody
		@RequestMapping(value="/insertMisEmpleados", method= RequestMethod.POST, produces = "application/json")
		ResponseEntity <ResponseDto> insertMisEmpleados(@RequestBody MisEmpleadosDTO nuevoEmpleado){
			final HttpHeaders httpHeaders = new HttpHeaders();
			
			ResponseDto response = misEmpleadosService.insertMisEmpleados(nuevoEmpleado);
			
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
		}
		
		
		
		// Sevicios Rest = respuestas con JSON -> { "nombre" : "Juan" } -> (json, texto, etc.)
		// Sevicios Soap = archivos xml
		
		
		
		
		// Eliminar registro
			@ResponseBody
		    @RequestMapping(value="/eliminarMiEmpleado", method = RequestMethod.POST, produces = "application/json")
			public ResponseEntity <ResponseDto> eliminarMiEmpleado(@RequestBody MisEmpleadosDTO idMiEmpleado){
				final HttpHeaders httpHeaders = new HttpHeaders();

				ResponseDto respuesta = misEmpleadosService.eliminarMiEmpleado(idMiEmpleado);
				
				httpHeaders.setContentType(MediaType.APPLICATION_JSON);
				
				return new ResponseEntity <ResponseDto> (respuesta, httpHeaders, HttpStatus.OK);	
		    }
		
			
			// Actualizar registro
			@ResponseBody
		    @RequestMapping(value="/actualizarDatosMisEmpleados", method = RequestMethod.POST, produces = "application/json")
			ResponseEntity < ResponseDto > actualizarDatosMisEmpleados(@RequestBody MisEmpleadosDTO datos){
				final HttpHeaders httpHeaders = new HttpHeaders();

				ResponseDto respuesta = misEmpleadosService.actualizarDatosMisEmpleados(datos);
				
				httpHeaders.setContentType(MediaType.APPLICATION_JSON);
				
				return new ResponseEntity <ResponseDto> (respuesta, httpHeaders, HttpStatus.OK);	
		    }
		
		
		
		
	
	
	
} // Fin de la clase
