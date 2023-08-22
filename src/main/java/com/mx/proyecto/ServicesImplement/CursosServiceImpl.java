package com.mx.proyecto.ServicesImplement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Repository.ICursosRepository;
import com.mx.proyecto.Services.ICursos;

@Service
public class CursosServiceImpl implements ICursos {

	@Autowired
	private ICursosRepository iCursosRepository;

	@Override
	public ResponseDto getCursos() {
		ResponseDto response = new ResponseDto();
		try {
			List<Cursos> lista = iCursosRepository.getCursos();

			if (lista != null && lista.size() > 0) {
				response.setCode(0);
				response.setMessage("Hay " + lista.size() + " registros que mostrar de la tabla Cursos");
				response.setContent(lista);

			} else {
				response.setCode(-1);
				response.setMessage("No hay registros que mostrar en la tabla Cursos");
			}
		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la tabla Cursos.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-3);
			response.setMessage("Sucedio un error, por favor verificar.");
		}

		return response;
	}

	//********** METODO INSERTCURSOS ***********
	@Autowired
	DataSource dataSource;
	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public Integer nombreCursos(String nom_Curso) {
		jdbcTemplate.setDataSource(getDataSource());
	    String sql = "SELECT COUNT(*) FROM CURSOS WHERE NOMBRE_CURSO = ?";
	    return this.jdbcTemplate.queryForObject(sql, Integer.class, nom_Curso);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public ResponseDto insertCursos(Cursos nuevoCurso) {
		ResponseDto response = new ResponseDto();
		String respuestaMsg = "";
		Integer respuesta = 0;
		String nom_Curso = "";
		
		try {
			nom_Curso = nuevoCurso.getNombre_Curso();
			System.out.println(nom_Curso);
			
			Integer ExistCursos =nombreCursos(nom_Curso);
			System.out.println(ExistCursos);
			
			if(ExistCursos == 0 ) {
				respuestaMsg = respuestaMsg + " 1.- El curso no existe en la tabla cursos. "+ " Los cursos disponibles son: "
			+ iCursosRepository.getNomCursos();
			}
			
			if (nuevoCurso.getNombre_Curso() == null || nuevoCurso.getNombre_Curso().equals("") || nuevoCurso.getNombre_Curso().length() > 30) {
				respuestaMsg = respuestaMsg + "\n 1.- El nombre del Maestro no puede ser nulo o mayor a 30 caracteres. ";
				System.out.println("validacion del nombre del maestro ");
			}

			if(nuevoCurso.getHoras() <=0 || nuevoCurso.getHoras() >= 60) {
				respuestaMsg = respuestaMsg + "\n 2.- No pueden ser mas de 60 horas o estar en nulo. ";
				System.out.println("validacion de la telefono ");
				
			}

			if(nuevoCurso.getEspecialidad() == null || nuevoCurso.getEspecialidad().equals("")) {
				respuestaMsg = respuestaMsg + "\n 3.- El campo de la especialidad no puede ser nulo.";
				System.out.println("validacion de la fecha ");
				
			}
			
			System.out.println(respuestaMsg);
			if(respuestaMsg.equals("")) {
			
			respuesta = iCursosRepository.insertCursos(nuevoCurso);
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
			response.setMessage("No existen registros en la tabla cursos.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-3);
			response.setMessage("Sucedio un error, por favor verificar.");
		}
		return response;
	}

//*************** METODO  ********************
	@Override
	public ResponseDto updateCursos(Cursos curso) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			
			respuesta = iCursosRepository.updateCursos(curso);
			
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
			response.setMessage("No existen registros en la tabla Cursos.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-3);
			response.setMessage("Sucedio un error, por favor verificar.");
		}
		return response;
		
	}

	
//************** METODO  *************************************
	@Override
	public ResponseDto deleteCursos(Cursos curso) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			
			respuesta = iCursosRepository.deleteCursos(curso);
			
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
			response.setMessage("No existen registros en la tabla Cursos.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-3);
			response.setMessage("Sucedio un error, por favor verificar.");
		}
		return response;
		
	}
	
	@Override
	public ResponseDto insertCursosMasivos(Cursos[] cursos) {
		ResponseDto response = new ResponseDto();
		List<Cursos> cursosList = new ArrayList();
		
		for(Cursos curso : cursos) {
			cursosList.add(curso);
		}
		
		iCursosRepository.insertCursosMasivo(cursosList);
		
		response.setMessage("Se insertaron correctamente los "+cursosList.size()+" de registros. ");
 		
		return response;
	}

}