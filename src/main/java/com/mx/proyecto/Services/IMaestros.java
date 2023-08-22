package com.mx.proyecto.Services;

import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Dto.ResponseDto;

public interface IMaestros {

	ResponseDto getMaestros(); //ESTE METODO OBTIENE ASPIRANTES
	ResponseDto insertMaestros(Maestros nuevoMaestro);
	ResponseDto updateMaestros(Maestros maestro);
	ResponseDto deleteMaestros(Maestros maestro);
	ResponseDto insertMaestrosMasivo(Maestros[] maestros);

}