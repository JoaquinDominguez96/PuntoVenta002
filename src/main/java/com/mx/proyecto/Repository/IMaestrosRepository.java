package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Dto.Maestros;

public interface IMaestrosRepository {
	List<Maestros> getMaestros();
	Integer insertMaestros(Maestros nuevoMaestro);
	Integer updateMaestros(Maestros maestro);
	Integer deleteMaestros(Maestros maestro);
	int[][] insertMaestrosMasivo(List<Maestros> maestros);


}