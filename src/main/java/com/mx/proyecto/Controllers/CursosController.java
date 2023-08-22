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
import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Services.ICursos;

@Controller
@RequestMapping("curso")
public class CursosController{
	


@Autowired
private ICursos cursos;

@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS Y NO UNA LISTA
@RequestMapping (value = "/getCursos", method = RequestMethod.GET, produces = "application/json")
ResponseEntity< ResponseDto> getCursos(){
	final HttpHeaders httpHeaders = new HttpHeaders();
	
	ResponseDto response = new ResponseDto();
	response = cursos.getCursos();
	
	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
}

@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS Y NO UNA LISTA
@RequestMapping (value = "/insertCursos", method = RequestMethod.POST, produces = "application/json")
ResponseEntity< ResponseDto> insertCursos(@RequestBody Cursos nuevoCurso){
	final HttpHeaders httpHeaders = new HttpHeaders();
	
	ResponseDto response = new ResponseDto();
	response = cursos.insertCursos(nuevoCurso);
	
	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
}

@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS Y NO UNA LISTA
@RequestMapping (value = "/updateCursos", method = RequestMethod.PUT, produces = "application/json")
ResponseEntity< ResponseDto> updateCursos(@RequestBody Cursos nuevoCurso){
	final HttpHeaders httpHeaders = new HttpHeaders();
	
	ResponseDto response = new ResponseDto();
	response = cursos.updateCursos(nuevoCurso);
	
	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
}

@ResponseBody
@RequestMapping(value = "/deleteCursos", method = RequestMethod.DELETE, produces = "application/json")
ResponseEntity< ResponseDto> deleteCursos(@RequestBody Cursos nuevoCurso){
	final HttpHeaders httpHeaders = new HttpHeaders();
	
	ResponseDto response = new ResponseDto();
	
	
	response = cursos.deleteCursos(nuevoCurso);
	
	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
}

@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS Y NO UNA LISTA
@RequestMapping (value = "/insertCursosMasivo", method = RequestMethod.POST, produces = "application/json")
ResponseEntity< ResponseDto> insertCursosMasivo(@RequestBody Cursos[] nuevosCursos){
	final HttpHeaders httpHeaders = new HttpHeaders();
	
	ResponseDto response = new ResponseDto();
	response = cursos.insertCursosMasivos(nuevosCursos);
	
	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
}

}