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
import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Services.IAspirantes;


	@Controller
	@RequestMapping("aspirante")
	public class AspirantesController{
		
	

	@Autowired
	private IAspirantes aspirantes;
	
	@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS Y NO UNA LISTA
	@RequestMapping (value = "/getAspirantes", method = RequestMethod.GET, produces = "application/json")
	ResponseEntity< ResponseDto> getAspirantes(){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
		response = aspirantes.getAspirantes();
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS Y NO UNA LISTA
	@RequestMapping (value = "/insertAspirantes", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity< ResponseDto> insertAspirantes(@RequestBody Aspirantes nuevoAspirante){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
		response = aspirantes.insertAspirantes(nuevoAspirante);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS Y NO UNA LISTA
	@RequestMapping (value = "/updateAspirantes", method = RequestMethod.PUT, produces = "application/json")
	ResponseEntity< ResponseDto> updateAspirantes(@RequestBody Aspirantes nuevoAspirante){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
		response = aspirantes.updateAspirantes(nuevoAspirante);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteAspirantes", method = RequestMethod.DELETE, produces = "application/json")
	ResponseEntity< ResponseDto> deleteAspirantes(@RequestBody Aspirantes nuevoAspirante){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
		
		
		response = aspirantes.deleteAspirantes(nuevoAspirante);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS Y NO UNA LISTA
	@RequestMapping (value = "/insertAspirantesMasivo", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity< ResponseDto> insertAspirantesMasivo(@RequestBody Aspirantes[] nuevosAspirantes){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = new ResponseDto();
		response = aspirantes.insertAspirantesMasivo(nuevosAspirantes);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
}

