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
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Services.UsuariosAdminService2;


@Controller
@RequestMapping("usuarioAdminHibernate2")
public class UsuariosAdminController2 {
	
	@Autowired
	public UsuariosAdminService2 usuariosAdminService2;
	
	// CRUD = registrar, actualizarm consulta y eliminar
	
	// json => un conjunto de atributos - valor -> nombre: isaul
	
	
	// lista de usuarios -> select * from tabla;
		@ResponseBody
		@RequestMapping(value="/getUsuariosAdmin2", method= RequestMethod.GET, produces = "application/json")
		public ResponseDto getUsuariosAdmin2() {
			
			return usuariosAdminService2.getUsuarios2();
		}
	
	
		
		
		
		// Servicio para inserta un nuevo usuario
		@ResponseBody
		@RequestMapping(value="/insertUsuarios2", method= RequestMethod.POST, produces = "application/json")
		ResponseEntity <ResponseDto> insertUsuarios(@RequestBody UsuariosAdminDTO nuevoUsuario){
			final HttpHeaders httpHeaders = new HttpHeaders();
			
			ResponseDto response = usuariosAdminService2.insertUsuariosAdmin(nuevoUsuario);
			
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
		}
		
		
		
		// Sevicios Rest = respuestas con JSON -> { "nombre" : "Juan" } -> (json, texto, etc.)
		// Sevicios Soap = archivos xml
		
		
		
		
		// Eliminar registro
			@ResponseBody
		    @RequestMapping(value="/eliminarUsuario2", method = RequestMethod.POST, produces = "application/json")
			public ResponseEntity <ResponseDto> eliminarUsuario(@RequestBody UsuariosAdminDTO idUser){
				final HttpHeaders httpHeaders = new HttpHeaders();

				ResponseDto respuesta = usuariosAdminService2.eliminarUsuario(idUser);
				
				httpHeaders.setContentType(MediaType.APPLICATION_JSON);
				
				return new ResponseEntity <ResponseDto> (respuesta, httpHeaders, HttpStatus.OK);	
		    }
		
			
			// Actualizar registro
			@ResponseBody
		    @RequestMapping(value="/actualizarDatos2", method = RequestMethod.POST, produces = "application/json")
			ResponseEntity < ResponseDto > actualizarDatosUsuario(@RequestBody UsuariosAdminDTO datos){
				final HttpHeaders httpHeaders = new HttpHeaders();

				ResponseDto respuesta = usuariosAdminService2.actualizarUsuario(datos);
				
				httpHeaders.setContentType(MediaType.APPLICATION_JSON);
				
				return new ResponseEntity <ResponseDto> (respuesta, httpHeaders, HttpStatus.OK);	
		    }
		
		
		
		
	
	
	
} // Fin de la clase
