package com.mx.proyecto.Services;

import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;

public interface UsuariosAdminService2 {

	ResponseDto getUsuarios2();

	ResponseDto insertUsuariosAdmin(UsuariosAdminDTO nuevoUsuario);

	ResponseDto eliminarUsuario(UsuariosAdminDTO idUser);

	ResponseDto actualizarUsuario(UsuariosAdminDTO datos);

	

}
