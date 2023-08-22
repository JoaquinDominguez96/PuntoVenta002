package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.mx.proyecto.Dto.Aspirantes;

public class AspirantesListMapper<T> implements RowMapper<Aspirantes> {
	
	public Aspirantes mapRow(ResultSet rs, int rowNum) throws SQLException{
		Aspirantes objeto = new Aspirantes();
		
		objeto.setIdAlumno(rs.getBigDecimal("IDALUMNO"));
		objeto.setNombreAlumno(rs.getString("NOMBREALUMNO"));
		objeto.setEdad(rs.getInt("EDAD"));
		objeto.setFechaInscripcion(rs.getDate("FECHAINSCRIPCION"));
//		objeto.setCursoId(rs.getBigDecimal("CURSOID"));
//		objeto.setMaestroId(rs.getBigDecimal("MAESTROID"));
		objeto.setCurso(rs.getString("NOMBRE_CURSO"));
		objeto.setMaestro(rs.getString("NOMBRE_MAESTRO"));
		return objeto;
	}

}
