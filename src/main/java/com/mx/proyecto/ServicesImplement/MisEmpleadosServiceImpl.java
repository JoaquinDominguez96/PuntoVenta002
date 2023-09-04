package com.mx.proyecto.ServicesImplement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.proyecto.Dto.MisEmpleadosDTO;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Repository.MisEmpleadosDAO;
import com.mx.proyecto.Services.MisEmpleadosService;
import com.mx.proyecto.entidad.MisEmpleados;

@Service
public class MisEmpleadosServiceImpl implements MisEmpleadosService {

	@Autowired
	private MisEmpleadosDAO misEmpleadosDAO;

	@Override
	public ResponseDto getEmpleados() {
		ResponseDto response = new ResponseDto();

		try {
			List<MisEmpleados> listaMisEmpleados = misEmpleadosDAO.obtieneMisEmpleados();
			System.out.println(listaMisEmpleados);
			if (listaMisEmpleados.isEmpty()) {
				response.setCode(100);
				response.setMessage(" No existen registros en la tabla Mis_Empleados. ");
			} else {
				response.setCode(200);
				response.setMessage(" Lista de Empleados: ");
				response.setList(listaMisEmpleados);
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage(" Ocurrios un error en el metodos getMisEmpleados en la clase MisEmpleadosServiceImpl");
		}
		return response;
	}

	@Override
	public ResponseDto getEmpleadosMasculinos() {
		ResponseDto response = new ResponseDto();
		try {
			List<MisEmpleados> listaMisEmpleadosMasc = misEmpleadosDAO.obtieneMisEmpleadosMasculinos();
			System.out.println(listaMisEmpleadosMasc);
			if (listaMisEmpleadosMasc.isEmpty()) {
				response.setCode(100);
				response.setMessage(" No existen registros en la tabla Mis_Empleados. ");
			} else {
				response.setCode(200);
				response.setMessage(" Lista de Empleados de sexo masculino son: ");
				response.setList(listaMisEmpleadosMasc);
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage(
					" Ocurrios un error en el metodos getEmpleadosMasculinos en la clase MisEmpleadosServiceImpl");
		}

		return response;
	}

	@SuppressWarnings("null")
	@Override
	public ResponseDto getEmpleadosF35() {
		ResponseDto response = new ResponseDto();
		try {
			List<MisEmpleados> listaMisEmpleadosF35 = misEmpleadosDAO.obtieneMisEmpleadosF35();
			System.out.println(listaMisEmpleadosF35);
			if (listaMisEmpleadosF35 == null && listaMisEmpleadosF35.isEmpty()) {
				response.setCode(100);
				response.setMessage(" No existen registros en la tabla Mis_Empleados. ");
			} else {
				response.setCode(200);
				response.setMessage(" Lista de Empleados de sexo femenino y de 35 años de edad son: ");
				response.setList(listaMisEmpleadosF35);
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage(
					" Ocurrios un error en el metodos getEmpleadosMasculinos en la clase MisEmpleadosServiceImpl");
		}

		return response;
	}

	@Override
	public ResponseDto getEmpleadosRFC(MisEmpleadosDTO nuevoEmpleadoRfc) {
		ResponseDto response = new ResponseDto();
		try {
			// MisEmpleados datos = new MisEmpleados();

			MisEmpleados listaMisEmpleadosRFC = misEmpleadosDAO.obtieneMisEmpleadosRFC(nuevoEmpleadoRfc);
			System.out.println(listaMisEmpleadosRFC);
			if (listaMisEmpleadosRFC == null) {
				response.setCode(100);
				response.setMessage(" No existen registros en la tabla Mis_Empleados. ");
			} else {
				response.setCode(200);
				response.setMessage(" El empleado con ese RFC es: ");
				response.setContent(listaMisEmpleadosRFC);
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage(
					" Ocurrios un error en el metodos getEmpleadosMasculinos en la clase MisEmpleadosServiceImpl");
		}

		return response;
	}

	@Override
	public ResponseDto insertMisEmpleados(MisEmpleadosDTO nuevoEmpleadoRfcYCurp) {
		ResponseDto response = new ResponseDto();
		String validaciones = "";
		/*
		 * 1.- Un service para insertar nuevos empleados REGLA I.- Antes de insertar un
		 * empleado verificar si ya EXISTE ese usuario en la base de datos
		 */
		try {
			MisEmpleados consultaMisEmpleadosRfcYCurp = misEmpleadosDAO.obtieneMisEmpleadosRfcYCurp(nuevoEmpleadoRfcYCurp);
			System.out.println(consultaMisEmpleadosRfcYCurp);
			if (nuevoEmpleadoRfcYCurp != null) {
//				Pattern patCurp = Pattern.compile("^[A-Z]{4}\\d{6}[HM]{1}(AS|BC|BS|CC|CH|CL|CM|CS|DF|DG|GR|GT|HG|JC|MC|MN|MS|NE|NL|NT|OC|PL|QR|QT|SL|SP|SR|TC|TL|TS|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d]{1}\\d{1}$");
//				Matcher matCurp = patCurp.matcher(nuevoEmpleadoRfcYCurp.getCurp());
//				if(matCurp.find()) {
//					Pattern patRfc = Pattern.compile("^([A-ZÑ\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])([A-Z]|[0-9]){2}([A]|[0-9]){1})?$");
//					Matcher matRfc = patRfc.matcher(nuevoEmpleadoRfcYCurp.getRfc());
//					if(matRfc.find()) {
//						long NewNss = nuevoEmpleadoRfcYCurp.getNss();
//						Pattern patNss = Pattern.compile("^[0-9]{10}$");
//						Matcher matNss = patNss.matcher(Long.toString(NewNss));
//						if(matNss.find()) {
				long NewNss = nuevoEmpleadoRfcYCurp.getNss();
				boolean validarrfc = misEmpleadosDAO.validarRFC(nuevoEmpleadoRfcYCurp.getRfc());
				boolean validarcurp = misEmpleadosDAO.validarCURP(nuevoEmpleadoRfcYCurp.getCurp());
				boolean validarnss = misEmpleadosDAO.validarNSS(Long.toString(NewNss));

				if (!validarrfc) {
					validaciones = validaciones + " El RFC del empleado no cuenta con la estrutura adecuada. ";
				}
				if (!validarcurp) {
					validaciones = validaciones + " La CURP del empleado no cuenta con la estrutura adecuada. ";
				}
				if (!validarnss) {
					validaciones = validaciones + " El campo NSS debe ser numero y tener una longitud de 10. ";
				}
				if (validaciones.equals("")) {
							if (consultaMisEmpleadosRfcYCurp == null) {
								MisEmpleados datos = new MisEmpleados();
			//					datos.setIdMiEmpleado(nuevoEmpleado.getIdMiEmpleado());
								datos.setIdMiEmpleado(misEmpleadosDAO.obtenerValorSecuenciaTabla());
								datos.setNombreCompleto(nuevoEmpleadoRfcYCurp.getNombreCompleto());
								datos.setRfc(nuevoEmpleadoRfcYCurp.getRfc());
								datos.setCurp(nuevoEmpleadoRfcYCurp.getCurp());
								datos.setEdad(nuevoEmpleadoRfcYCurp.getEdad());
								datos.setSexo(nuevoEmpleadoRfcYCurp.getSexo());
								datos.setDireccion(nuevoEmpleadoRfcYCurp.getDireccion());
								datos.setNss(nuevoEmpleadoRfcYCurp.getNss());
								datos.setTelefono(nuevoEmpleadoRfcYCurp.getTelefono());
								datos.setActivo(nuevoEmpleadoRfcYCurp.getActivo());
			
								misEmpleadosDAO.create(datos); // Desde aqui se hace el insert
								response.setCode(200);
								response.setMessage(" Los datos del empleados se guardaron correctamente. ");
							}else {
								response.setCode(300);
								response.setMessage(" Los datos del empleado no pueden ser insertados porque ya existe su registro. ");
							}
				} else {
					response.setCode(300);
					response.setMessage(validaciones);
				}
//						}else {
//							response.setCode(300);
//							response.setMessage(" El campo NSS debe ser numero y tener una longitud de 10. ");
//						}
//						
//					}else {
//						response.setCode(300);
//						response.setMessage(" El RFC no cuenta con la estrutura adecuada. ");
//					}
//
//				}else {
//					response.setCode(100);
//					response.setMessage(" La CURP no cuenta con la estrutura adecuada. ");
//				}
			} else {
				response.setCode(400);
				response.setMessage(" Los datos del empleado vienen vacios. )");
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage(
					" Ocurrio un error en la clase MisEmpleadosServiceImpl en el metodo insertMisEmpleados. ");
		}

		return response;
	}

	@Override
	public ResponseDto eliminarMiEmpleado(MisEmpleadosDTO MisEmpleadosActivos) {
		ResponseDto response = new ResponseDto();

		/*
		 * 2.- Servicio para eliminar un empleado REGLA I.- Se puede eliminar siempre y
		 * cuando este dado de baja, si no esta dado de baja ese empleado mostrar un
		 * mensaje de -> "Imposible eliminar Empleado, Sigue Laborando (Activo)...! ",
		 * En caso contrario el siguiente mensaje -> "Empleado eliminado correctamente"
		 */

		try {
			MisEmpleados consultaMisEmpleadosActivos = misEmpleadosDAO.obtieneMisEmpleadosActivos(MisEmpleadosActivos);
			System.out.println(consultaMisEmpleadosActivos);
			if (MisEmpleadosActivos.getIdMiEmpleado() != 0) {
				if (consultaMisEmpleadosActivos == null) {
					misEmpleadosDAO.delete(MisEmpleadosActivos.getIdMiEmpleado());
					response.setCode(200);
					response.setMessage(" Empleado eliminado correctamente. ");
				} else {
					response.setCode(100);
					response.setMessage(" Imposible eliminar Empleado, Sigue Laborando (Activo)...!  ");
				}

			} else {
				response.setCode(400);
				response.setMessage(" El PK de la tabala Mis_Empleados viene vacio. ");
			}

		} catch (Exception e) {
			response.setCode(500);
			response.setMessage(
					" Ocurrio un error en la clase MisEmpleadosServiceImpl en el metodo eliminarMiEmpleado. ");
		}

		return response;
	}

	@Override
	public ResponseDto actualizarDatosMisEmpleados(MisEmpleadosDTO datos) {
		ResponseDto response = new ResponseDto();

		try {

			/*
			 * 3.- Servicio para actualizar los datos de un empleado REGLA I.- Para poder
			 * actualizar los datos el empleado debe de estar en estatus ACTIVO (Si no esta
			 * activo lanzar el mensaje ->
			 * "El empleado esta dado de baja, No puede actualizar su informacion.! ")
			 */
			MisEmpleados consultaMisEmpleadosActivos = misEmpleadosDAO.obtieneMisEmpleadosActivos(datos);
			System.out.println(consultaMisEmpleadosActivos);
			if (consultaMisEmpleadosActivos != null) {
				MisEmpleados datos2 = new MisEmpleados();
				datos2.setIdMiEmpleado(datos.getIdMiEmpleado());// duda de si pedir el id en postman
				datos2.setNombreCompleto(datos.getNombreCompleto());
				datos2.setRfc(datos.getRfc());
				datos2.setCurp(datos.getCurp());
				datos2.setEdad(datos.getEdad());
				datos2.setSexo(datos.getSexo());
				datos2.setDireccion(datos.getDireccion());
				datos2.setNss(datos.getNss());
				datos2.setTelefono(datos.getTelefono());
				datos2.setActivo(datos.getActivo());// PEDIR EN POSTMAN O NO

				misEmpleadosDAO.update(datos2); // Desde aqui se hace el insert
				response.setCode(200);
				response.setMessage(" El registro del empleado se actualizo correctamente. )");
			} else {
				response.setCode(100);
				response.setMessage(" El empleado esta dado de baja, No puede actualizar su informacion.! )");
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage(
					" Ocurrio un error en la clase UsuariosAdminServiceImpl2 en el metodo actualizarDatosMisEmpleados. )");
		}

		return response;
	}

} // fin de la clase
