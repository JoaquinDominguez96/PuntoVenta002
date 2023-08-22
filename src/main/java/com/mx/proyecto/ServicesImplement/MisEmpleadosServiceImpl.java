package com.mx.proyecto.ServicesImplement;

import java.util.List;
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
	public ResponseDto insertMisEmpleados(MisEmpleadosDTO nuevoEmpleado) {
		ResponseDto response = new ResponseDto();
		/*
		 * 1.- Un service para insertar nuevos empleados REGLA I.- Antes de insertar un
		 * empleado verificar si ya EXISTE ese usuario en la base de datos
		 */

		try {
			if (nuevoEmpleado != null) {
				if (nuevoEmpleado.getNombreCompleto() != null && !nuevoEmpleado.getNombreCompleto().isEmpty()
						&& nuevoEmpleado.getEdad() != 0 && nuevoEmpleado.getDireccion() != null
						&& !nuevoEmpleado.getDireccion().isEmpty()) {

					MisEmpleados datos = new MisEmpleados();
//					datos.setIdMiEmpleado(nuevoEmpleado.getIdMiEmpleado());
					datos.setIdMiEmpleado(misEmpleadosDAO.obtenerValorSecuenciaTabla());
					datos.setNombreCompleto(nuevoEmpleado.getNombreCompleto());
					datos.setRfc(nuevoEmpleado.getRfc());
					datos.setCurp(nuevoEmpleado.getCurp());
					datos.setEdad(nuevoEmpleado.getEdad());
					datos.setSexo(nuevoEmpleado.getSexo());
					datos.setDireccion(nuevoEmpleado.getDireccion());
					datos.setNss(nuevoEmpleado.getNss());
					datos.setTelefono(nuevoEmpleado.getTelefono());
					datos.setActivo(nuevoEmpleado.getActivo());

					misEmpleadosDAO.create(datos); // Desde aqui se hace el insert
					response.setCode(200);
					response.setMessage(" Los datos del empleados se guardaron correctamente. )");

				} else {
					response.setCode(300);
					response.setMessage(
							" Los datos obligatorios del empleado vienen vacios - (Nombre completo, edad y direccion. )");
				}
			} else {
				response.setCode(400);
				response.setMessage(" Los datos del empleado vienen vacios. )");
			}

		} catch (Exception e) {
			response.setCode(500);
			response.setMessage(
					" Ocurrio un error en la clase MisEmpleadosServiceImpl en el metodo insertMisEmpleados. )");
		}

		return response;
	}

	@Override
	public ResponseDto eliminarMiEmpleado(MisEmpleadosDTO idMiEmpleado) {
		ResponseDto response = new ResponseDto();

		/*
		 * 2.- Servicio para eliminar un empleado REGLA I.- Se puede eliminar siempre y
		 * cuando este dado de baja, si no esta dado de baja ese empleado mostrar un
		 * mensaje de -> "Imposible eliminar Empleado, Sigue Laborando (Activo)...! ",
		 * En caso contrario el siguiente mensaje -> "Empleado eliminado correctamente"
		 */

		try {
			System.out.println(idMiEmpleado);
			System.out.println(idMiEmpleado.getIdMiEmpleado());
			System.out.println(idMiEmpleado.getNombreCompleto());
			System.out.println(idMiEmpleado.getActivo());
			
//			MisEmpleadosDTO EmpleadoActivo = new MisEmpleadosDTO();
//			EmpleadoActivo = misEmpleadosDAO.obtenerDatosPorActivo(long idMiEmpleado);

			if (idMiEmpleado.getIdMiEmpleado() != 0) {
				if (misEmpleadosDAO.saberSiEstaActivo() != 1) {
					misEmpleadosDAO.delete(idMiEmpleado.getIdMiEmpleado());
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
					" Ocurrio un error en la clase MisEmpleadosServiceImpl en el metodo eliminarMiEmpleado. )");
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
			if (datos.getActivo() != 0) {
				MisEmpleados datos2 = new MisEmpleados();
				datos2.setIdMiEmpleado(datos.getIdMiEmpleado());
				datos2.setNombreCompleto(datos.getNombreCompleto());
				datos2.setRfc(datos.getRfc());
				datos2.setCurp(datos.getCurp());
				datos2.setEdad(datos.getEdad());
				datos2.setSexo(datos.getSexo());
				datos2.setDireccion(datos.getDireccion());
				datos2.setNss(datos.getNss());
				datos2.setTelefono(datos.getTelefono());
				datos2.setActivo(datos.getActivo());

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
