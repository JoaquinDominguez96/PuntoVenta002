package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Dto.MisEmpleadosDTO;
import com.mx.proyecto.entidad.MisEmpleados;

public interface MisEmpleadosDAO extends DAO<MisEmpleados, Long> {

	List<MisEmpleados> obtieneMisEmpleados();

	Long obtenerValorSecuenciaTabla();

	List<MisEmpleados> obtieneMisEmpleadosMasculinos();

	List<MisEmpleados> obtieneMisEmpleadosF35();

	MisEmpleados obtieneMisEmpleadosRFC(MisEmpleadosDTO nuevoEmpleadoRfc);

	MisEmpleados obtieneMisEmpleadosRfcYCurp(MisEmpleadosDTO nuevoEmpleadoRfcYCurp);

	MisEmpleados obtieneMisEmpleadosActivos(MisEmpleadosDTO MisEmpleadosActivos);


	
	

}
