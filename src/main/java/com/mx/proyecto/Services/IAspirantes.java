package com.mx.proyecto.Services;

import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Dto.ResponseDto;

public interface IAspirantes {

	ResponseDto getAspirantes(); //ESTE METODO OBTIENE ASPIRANTES
	ResponseDto insertAspirantes(Aspirantes nuevoAspirante);
	ResponseDto updateAspirantes(Aspirantes aspirante);
	ResponseDto deleteAspirantes(Aspirantes aspirante);
	ResponseDto insertAspirantesMasivo(Aspirantes[] aspirantes);

}
