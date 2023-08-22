 package com.mx.proyecto.Repository;

import java.util.List;
import com.mx.proyecto.entidad.UsuariosAdmin;

public interface UsuariosAdminDAO2 extends DAO<UsuariosAdmin, Long> {

	List<UsuariosAdmin> obtieneUsuarios();
	
	

}
