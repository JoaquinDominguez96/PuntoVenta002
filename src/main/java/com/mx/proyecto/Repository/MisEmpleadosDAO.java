package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.entidad.MisEmpleados;

public interface MisEmpleadosDAO extends DAO<MisEmpleados, Long> {

	List<MisEmpleados> obtieneMisEmpleados();

	Long obtenerValorSecuenciaTabla();

	Long saberSiEstaActivo();


	
	

}
