package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.proyecto.Dto.Cursos;

public class ValidaCursoListMapper<T> implements RowMapper<Cursos> {
	
	public Cursos mapRow(ResultSet rs, int rowNum) throws SQLException{
		Cursos objeto = new Cursos();
		
		objeto.setCursoId(rs.getBigDecimal("CURSOID"));

		return objeto;
	}

}