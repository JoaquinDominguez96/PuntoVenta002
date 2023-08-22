package com.mx.proyecto.ServicesImplement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Repository.IAspirantesRepository;
import com.mx.proyecto.Services.IAspirantes;


@Service
public class AspirantesServiceImpl implements IAspirantes {

	@Autowired
	private IAspirantesRepository iAspirantesRepository;
//	private ICursosRepository iCursosRepository;

	@Override
	public ResponseDto getAspirantes() {
		ResponseDto response = new ResponseDto();
		try {
			List<Aspirantes> lista = iAspirantesRepository.getAspirantes();

			if (lista != null && lista.size() > 0) {
				response.setCode(0);
				response.setMessage("Hay " + lista.size() + " registros que mostrar de la tabla aspirantes");
				response.setContent(lista);

			} else {
				response.setCode(-1);
				response.setMessage("No hay registros que mostrar en la tabla aspirantes");
			}
		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la tabla aspirantes.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-3);
			response.setMessage("Sucedio un error, por favor verificar.");
		}

		return response;
	}

	//********** METODO INSERTASPIRANTES ***********
	
	@Override
	public ResponseDto insertAspirantes(Aspirantes nuevoAspirante) {
		ResponseDto response = new ResponseDto();
		String respuestaMsg = "";
		Integer respuesta = 0;
		BigDecimal id = new BigDecimal(0);
		BigDecimal hayValMaestros = new BigDecimal(0);
		BigDecimal hayValCursos = new BigDecimal(0);
		
		try {

			id= nuevoAspirante.getCursoId();
			BigDecimal hayCursos = iAspirantesRepository.cantidadCursos(id);
			if(hayCursos.compareTo(hayValCursos) == 0) {
				respuestaMsg = respuestaMsg + " 1.- El curso no existe en la tabla cursos. "+ " Los cursos disponibles son: "
			+ iAspirantesRepository.getCursos();
			}
			id= nuevoAspirante.getMaestroId();
			BigDecimal hayMaestros = cantidadMaestros(id);
			if(hayMaestros.compareTo(hayValMaestros) == 0) {
				respuestaMsg = respuestaMsg + " 2.- El maestro no existe en la tabla maestros. "+ " Los maestros disponibles son: "
			+ iAspirantesRepository.getMaestros();
			}
			
			if (nuevoAspirante.getNombreAlumno() == null || nuevoAspirante.getNombreAlumno().equals("") || nuevoAspirante.getNombreAlumno().length() > 30) {
				respuestaMsg = respuestaMsg + " 3.- El nombre del alumno no puede ser nulo o mayor a 30 caracteres. ";
				System.out.println("validacion del nombre ");
			}
			if(nuevoAspirante.getEdad() <=0 || nuevoAspirante.getEdad() >= 150) {
				respuestaMsg = respuestaMsg + " 4.- La edad no puede ser menor a 0 o mayor de 150 años.";
				System.out.println("validacion de la edad ");
				
			}
			
			if(nuevoAspirante.getFechaInscripcion() == null || nuevoAspirante.getFechaInscripcion().equals("")) {
				respuestaMsg = respuestaMsg + " 5.- La fecha no puede ser nula.";
				System.out.println("validacion de la fecha ");
				
			}
			if(nuevoAspirante.getMaestroId() == null) {
				respuestaMsg = respuestaMsg + " 6.- El campo MaestroID no puede ser nulo.";
				System.out.println("validacion del maestroid ");
			}
			if(nuevoAspirante.getCursoId() == null) {
				respuestaMsg = respuestaMsg + " 7.- El campo CursoId no puede ser nulo.";
				System.out.println("validacion del cursoid ");
			}
			System.out.println(respuestaMsg);
			if(respuestaMsg.equals("")) {
			
			respuesta = iAspirantesRepository.insertAspirantes(nuevoAspirante);
			System.out.println("validacion de la insersion ");
			
			if (respuesta == 1) {
				response.setCode(0);
				response.setMessage("Se inserto correctamente el registro.");

			} else {
				response.setCode(-1);
				response.setMessage("No se inserto correctamente el registro");
			}
			}
			else {
				response.setCode(-4);
				response.setMessage(respuestaMsg);
			}
				
		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la tabla aspirantes.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-3);
			response.setMessage("Sucedio un error, por favor verificar.");
		}
		return response;
	}

//*************** METODO UPDATEASPIRANTES ********************
	@Override
	public ResponseDto updateAspirantes(Aspirantes aspirante) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			
			respuesta = iAspirantesRepository.updateAspirantes(aspirante);
			
			if (respuesta == 1) {
				response.setCode(0);
				response.setMessage("Se actualizó correctamente el registro.");
			} else {
				response.setCode(-1);
				response.setMessage("No se actualizó correctamente el registro");
			}
		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la tabla aspirantes.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-3);
			response.setMessage("Sucedio un error, por favor verificar.");
		}
		return response;
		
	}

	
//************** METODO DELETEASPIRANTES *************************************
	@Override
	public ResponseDto deleteAspirantes(Aspirantes aspirante) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			
			respuesta = iAspirantesRepository.deleteAspirantes(aspirante);
			
			if (respuesta == 1) {
				response.setCode(0);
				response.setMessage("Se borro correctamente el registro.");
			} else {
				response.setCode(-1);
				response.setMessage("No se borro correctamente el registro");
			}
		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la tabla aspirantes.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-3);
			response.setMessage("Sucedio un error, por favor verificar.");
		}
		return response;
		
	}

	@Override
	public ResponseDto insertAspirantesMasivo(Aspirantes[] aspirantes) {
		ResponseDto response = new ResponseDto();
		String respuestaMsg = "";
		List<Aspirantes> aspirantesList = new ArrayList();
		
		for(Aspirantes aspirante : aspirantes) {
			aspirantesList.add(aspirante);
			System.out.println(aspirante);
//			if(!aspirante.getMaestroId().equals(aspirante.getMaestroId())) {
//				respuestaMsg =(" No se puede insertar registro por que no existe el maestro. ");
//			}
		}
		
		iAspirantesRepository.insertAspirantesMasivo(aspirantesList);
		
		response.setMessage("Se insertaron correctamente los "+aspirantesList.size()+" de registros. ");
 		
		return response;
	}

}
