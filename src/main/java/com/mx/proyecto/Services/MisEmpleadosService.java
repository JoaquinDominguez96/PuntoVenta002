package com.mx.proyecto.Services;

import com.mx.proyecto.Dto.MisEmpleadosDTO;
import com.mx.proyecto.Dto.ResponseDto;

public interface MisEmpleadosService {

	ResponseDto getEmpleados();

	ResponseDto insertMisEmpleados(MisEmpleadosDTO nuevoEmpleado);

	ResponseDto eliminarMiEmpleado(MisEmpleadosDTO idMiEmpleado);

	ResponseDto actualizarDatosMisEmpleados(MisEmpleadosDTO datos);

	

}
