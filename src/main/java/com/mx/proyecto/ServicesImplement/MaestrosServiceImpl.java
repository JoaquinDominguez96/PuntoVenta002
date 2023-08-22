package com.mx.proyecto.ServicesImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Repository.IMaestrosRepository;
import com.mx.proyecto.Services.IMaestros;

@Service
public class MaestrosServiceImpl implements IMaestros {

	@Autowired
	private IMaestrosRepository iMaestrosRepository;

	@Override
	public ResponseDto getMaestros() {
		ResponseDto response = new ResponseDto();
		try {
			List<Maestros> lista = iMaestrosRepository.getMaestros();

			if (lista != null && lista.size() > 0) {
				response.setCode(0);
				response.setMessage("Hay " + lista.size() + " registros que mostrar de la tabla Maestros");
				response.setContent(lista);

			} else {
				response.setCode(-1);
				response.setMessage("No hay registros que mostrar en la tabla Maestros");
			}
		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la tabla Maestros.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-3);
			response.setMessage("Sucedio un error, por favor verificar.");
		}

		return response;
	}

	//********** METODO  ***********
	@Override
	public ResponseDto insertMaestros(Maestros nuevoMaestro) {
		ResponseDto response = new ResponseDto();
		String respuestaMsg = "";
		Integer respuesta = 0;
		
		try {
			
			if (nuevoMaestro.getNombre_maestro() == null || nuevoMaestro.getNombre_maestro().equals("") || nuevoMaestro.getNombre_maestro().length() > 30) {
				respuestaMsg = respuestaMsg + "\n 1.- El nombre del Maestro no puede ser nulo o mayor a 30 caracteres. ";
				System.out.println("validacion del nombre del maestro ");
			}

			if(nuevoMaestro.getCorreo() == null || nuevoMaestro.getCorreo().equals("")) {
				respuestaMsg = respuestaMsg + "\n 2.- El correo no puede ser nulo.";
				System.out.println("validacion de la fecha ");
				
			}
			if(nuevoMaestro.getTelefono() <=0 || nuevoMaestro.getTelefono() == null) {
				respuestaMsg = respuestaMsg + "\n 3.- El telefono del maestro no puede ser nulo. ";
				System.out.println("validacion de la telefono ");
				
			}
			
			System.out.println(respuestaMsg);
			if(respuestaMsg.equals("")) {
			
			respuesta = iMaestrosRepository.insertMaestros(nuevoMaestro);
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

//*************** METODO  ********************
	@Override
	public ResponseDto updateMaestros(Maestros maestro) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			
			respuesta = iMaestrosRepository.updateMaestros(maestro);
			
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
			response.setMessage("No existen registros en la tabla Maestros.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-3);
			response.setMessage("Sucedio un error, por favor verificar.");
		}
		return response;
		
	}

	
//************** METODO  *************************************
	@Override
	public ResponseDto deleteMaestros(Maestros maestro) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;
		
		try {
			
			respuesta = iMaestrosRepository.deleteMaestros(maestro);
			
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
			response.setMessage("No existen registros en la tabla Maestros.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-3);
			response.setMessage("Sucedio un error, por favor verificar.");
		}
		return response;
		
	}
	
	@Override
	public ResponseDto insertMaestrosMasivo(Maestros[] maestros) {
		ResponseDto response = new ResponseDto();
		List<Maestros> maestrosList = new ArrayList();
		
		for(Maestros maestro : maestros) {
			maestrosList.add(maestro);
		}
		
		iMaestrosRepository.insertMaestrosMasivo(maestrosList);
		
		response.setMessage("Se insertaron correctamente los "+maestrosList.size()+" de registros. ");
 		
		return response;
	}

}