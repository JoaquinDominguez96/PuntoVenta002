package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.proyecto.Dto.Maestros;

public class MaestrosListMapper<T> implements RowMapper<Maestros> {
	
	public Maestros mapRow(ResultSet rs, int rowNum) throws SQLException{
		Maestros objeto = new Maestros();
		
		objeto.setMaestroId(rs.getBigDecimal("MAESTROID"));
		objeto.setNombre_maestro(rs.getString("NOMBRE_MAESTRO"));
		objeto.setCorreo(rs.getString("CORREO"));
		objeto.setTelefono(rs.getLong("TELEFONO"));

		return objeto;
	}

}