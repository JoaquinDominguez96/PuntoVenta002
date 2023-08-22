package com.mx.proyecto.ServicesImplement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Repository.UsuariosAdminDAO2;
import com.mx.proyecto.Services.UsuariosAdminService2;
import com.mx.proyecto.entidad.UsuariosAdmin;

@Service
public class UsuariosAdminServiceImpl2 implements UsuariosAdminService2 {

	@Autowired
	private UsuariosAdminDAO2 usuariosAdminDAO2;

	@Override
	public ResponseDto getUsuarios2() {
		ResponseDto response = new ResponseDto();

		try {
			List<UsuariosAdmin> listaUsuarios = usuariosAdminDAO2.obtieneUsuarios();
			System.out.println(listaUsuarios);
			if (listaUsuarios.isEmpty()) {
				response.setCode(100);
				response.setMessage(" No existen registros. ");
			} else {
				response.setCode(200);
				response.setMessage(" Lista de Usuarios: ");
				response.setList(listaUsuarios);
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage(" Ocurrios un error en el metodos getUsuarios2 en la clase UsuariosAdminServiceImpl2");
		}
		return response;
	}

	@Override
	public ResponseDto insertUsuariosAdmin(UsuariosAdminDTO nuevoUsuario) {
		ResponseDto response = new ResponseDto();
		
		try {
			if(nuevoUsuario != null) {
				if(nuevoUsuario.getNombreCompleto() != null && !nuevoUsuario.getNombreCompleto().isEmpty()&&
						nuevoUsuario.getEdad() != 0 &&
						nuevoUsuario.getDireccion() != null && !nuevoUsuario.getDireccion().isEmpty()) {
					
					UsuariosAdmin datos = new UsuariosAdmin(); 
					datos.setIdUser(nuevoUsuario.getIdUser());
					datos.setNombreCompleto(nuevoUsuario.getNombreCompleto());
					datos.setDireccion(nuevoUsuario.getDireccion());
					datos.setEdad(nuevoUsuario.getEdad());
					datos.setEstado(nuevoUsuario.getEstado());
					
					usuariosAdminDAO2.create(datos); //Desde aqui se hace el insert
					response.setCode(200);
					response.setMessage(" Los datos se guardaron correctamente. )");
					
				}else {
					response.setCode(300);
					response.setMessage(" Los datos obligatorios vienen vacios - (Nombre completo, edad y direccion. )");
				}
			}else {
				response.setCode(400);
				response.setMessage(" Los datos vienen vacios. )");
			}
			
		}catch (Exception e) {
			response.setCode(500);
			response.setMessage(" Ocurrio un error en la clase UsuariosAdminServiceImpl2 en el metodo insertUsuariosAdmin. )");
		}
		
		
		return response;
	}

	@Override
	public ResponseDto eliminarUsuario(UsuariosAdminDTO idUser) {
		ResponseDto response = new ResponseDto();
		
		try {
			if(idUser.getIdUser() != 0) {
				usuariosAdminDAO2.delete(idUser.getIdUser());
				response.setCode(200);
				response.setMessage(" El registro se elimino correctamente. ");
			}else {
				response.setCode(400);
				response.setMessage(" El PK viene vacio. ");
			}
			
		}catch (Exception e) {
			response.setCode(500);
			response.setMessage(" Ocurrio un error en la clase UsuariosAdminServiceImpl2 en el metodo eliminarUsuario. )");
		}
		
		return response;
	}

	@Override
	public ResponseDto actualizarUsuario(UsuariosAdminDTO datos) {
		ResponseDto response = new ResponseDto();
		
		try {
			
			UsuariosAdmin datos2 = new UsuariosAdmin(); 
			datos2.setIdUser(datos.getIdUser());
			datos2.setNombreCompleto(datos.getNombreCompleto());
			datos2.setDireccion(datos.getDireccion());
			datos2.setEdad(datos.getEdad());
			datos2.setEstado(datos.getEstado());
			
			usuariosAdminDAO2.update(datos2); //Desde aqui se hace el insert
			response.setCode(200);
			response.setMessage(" Los registro se actualizo correctamente. )");
			
		}catch (Exception e) {
			response.setCode(500);
			response.setMessage(" Ocurrio un error en la clase UsuariosAdminServiceImpl2 en el metodo actualizarUsuario. )");
		}
		
		return response;
	}

} // fin de la clase
