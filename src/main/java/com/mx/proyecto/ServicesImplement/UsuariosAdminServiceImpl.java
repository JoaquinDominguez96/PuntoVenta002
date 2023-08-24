package com.mx.proyecto.ServicesImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Repository.UsuariosAdminDAO;
import com.mx.proyecto.Services.UsuariosAdminService;
import com.mx.proyecto.entidad.UsuariosAdmin;

@Service
public class UsuariosAdminServiceImpl implements UsuariosAdminService{
	
	@Autowired
	private UsuariosAdminDAO usuariosAdminDAO;

	
	@Override
	public List<UsuariosAdmin> getUsuarios() { // desarrollo del metodo
		
		List<UsuariosAdmin> listUsuarios = usuariosAdminDAO.obtenerTodosLosDatos();
		
		return listUsuarios;
	}
	
	@Override
	public List<UsuariosAdmin> getUsuariosPorEdad(UsuariosAdminDTO nuevoUsuarioPorEdad){ // desarrollo del metodo
		
		List<UsuariosAdmin> listUsuarios = usuariosAdminDAO.obtenerTodosLosDatosPorEdad(nuevoUsuarioPorEdad.getEdad());
		
		return listUsuarios;
	}


	
	
	@Override
	public ResponseDto insertUsuariosAdmin(UsuariosAdminDTO nuevoUsuario) {
		ResponseDto response = new ResponseDto();
/** Los datos que vienen desde postman vienen en el objeto -> nuevoUsuario
//	nuevoUsuario =	{
//			"idUser" : "1",
//			"nombreCompleto" : "x",
//			"edad" : "18",
//			"direccion" : "calle x",
//		}
*/
		
//		REGLA PARA TRABAJAR CON HIBERNATE SE USA UNA ENTIDAD PARA TRASPASAR LA INFORMACION
		
//		insert into TABLA(CAMPOS) values(?,?,?,?,?) --> JDBC
//		crear objeto de tipo entidad y pasar la informacion
		
//		es crear un objeto ->datos, como tiene el  = new UsuariosAdmin() es como inicializar algo nuevo (un obj vacio)
		UsuariosAdmin datos = new UsuariosAdmin(); 
		datos.setIdUser(nuevoUsuario.getIdUser());
		datos.setNombreCompleto(nuevoUsuario.getNombreCompleto());
		datos.setDireccion(nuevoUsuario.getDireccion());
		datos.setEdad(nuevoUsuario.getEdad());
		datos.setEstado(nuevoUsuario.getEstado());
		
//		datos =	{
//		"idUser" : "1",
//		"nombreCompleto" : "x",
//		"edad" : "18",
//		"direccion" : "calle x",
//	}
		
		Integer respuesta = usuariosAdminDAO.insertarDatosHibernate(datos);
		
		if(respuesta == 1) {
			response.setMessage("Se inserto correctamente");
			response.setCode(200); // OK
		}
		else {
			response.setMessage("No se inserto correctamente");
			response.setCode(500);
		}
		
		return response;
	}



	

	@Override
	public ResponseDto eliminarUsuario(UsuariosAdminDTO idUser) {
		ResponseDto response = new ResponseDto();
		
		UsuariosAdmin datoEliminar = new UsuariosAdmin();
		datoEliminar.setIdUser(idUser.getIdUser());
		
		Integer respuestaDel = usuariosAdminDAO.eliminarUser(datoEliminar);
		if(respuestaDel == 1) {
			response.setMessage("Se elimino correctamente");
			response.setCode(200); // OK
		}
		else {
			response.setMessage("No se pudo eliminar correctamente");
			response.setCode(500);
		}
		
		return response;
	}




	@Override
	public ResponseDto actualizarUsuario(UsuariosAdmin datos) {
		ResponseDto response = new ResponseDto();
		
		Integer respuestaUpdate = usuariosAdminDAO.actualizaInfo(datos);
		
		if(respuestaUpdate == 1) {
			response.setMessage("Se actualizo correctamente");
			response.setCode(200); // OK
		}
		else {
			response.setMessage("No se pudo actualizar correctamente");
			response.setCode(500);
		}
		
		return response;
	}
	
	
	
	
	
	

} // fin de la clase
