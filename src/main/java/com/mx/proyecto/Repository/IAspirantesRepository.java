package com.mx.proyecto.Repository;

import java.math.BigDecimal;
import java.util.List;

import com.mx.proyecto.Dto.Aspirantes;

public interface IAspirantesRepository {
	List<Aspirantes> getAspirantes();
	Integer insertAspirantes(Aspirantes nuevoAspirante);
	Integer updateAspirantes(Aspirantes aspirante);
	Integer deleteAspirantes(Aspirantes aspirante);
	int[][] insertAspirantesMasivo(List<Aspirantes> aspirantes);
	BigDecimal cantidadCursos(BigDecimal id);
	BigDecimal cantidadMaestros(BigDecimal id);

}
