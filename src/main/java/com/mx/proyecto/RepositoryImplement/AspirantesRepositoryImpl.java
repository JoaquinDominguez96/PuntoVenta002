package com.mx.proyecto.RepositoryImplement;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
//import org.springframework.jdbc.support.JdbcAccessor;
import org.springframework.stereotype.Repository;
import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Mapper.AspirantesListMapper;
//import com.mx.proyecto.Mapper.AspirantesObjectMapper;
import com.mx.proyecto.Repository.IAspirantesRepository;

@Repository
public class AspirantesRepositoryImpl implements IAspirantesRepository {

	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Override
	public List<Aspirantes> getAspirantes(){
		jdbcTemplate.setDataSource(getDataSource());
//		return jdbcTemplate.query("SELECT * FROM ASPIRANTES", new AspirantesListMapper<Aspirantes>());
		
		return jdbcTemplate.query("SELECT A.IDALUMNO, A.NOMBREALUMNO, A.EDAD, A.FECHAINSCRIPCION, C.NOMBRE_CURSO, M.NOMBRE_MAESTRO"
				+" FROM ASPIRANTES A JOIN CURSOS C ON A.CURSOID = C.CURSOID JOIN MAESTROS M ON A.MAESTROID = M.MAESTROID",
				new AspirantesListMapper<Aspirantes>());
	}
	
	@Override
	public BigDecimal cantidadCursos(BigDecimal id) {
		jdbcTemplate.setDataSource(getDataSource());
	    String sql = "SELECT COUNT(*) FROM CURSOS WHERE CURSOID = ?";
	    return this.jdbcTemplate.queryForObject(sql, BigDecimal.class, id);
	}
	
	@Override
	public BigDecimal cantidadMaestros(BigDecimal id) {
		jdbcTemplate.setDataSource(getDataSource());
	    String sql = "SELECT COUNT(*) FROM MAESTROS WHERE MAESTROID = ?";
	    return this.jdbcTemplate.queryForObject(sql, BigDecimal.class, id);
	}
	

	@Override
	public Integer insertAspirantes(Aspirantes nuevoAspirante) {
		jdbcTemplate.setDataSource(getDataSource());
		return 
		jdbcTemplate.update("INSERT INTO ASPIRANTES (NOMBREALUMNO, EDAD, FECHAINSCRIPCION, CURSOID, MAESTROID)"
				+ "VALUES (?,?,?,?,?)", nuevoAspirante.getNombreAlumno(), nuevoAspirante.getEdad(),
				nuevoAspirante.getFechaInscripcion(), nuevoAspirante.getCursoId(), nuevoAspirante.getMaestroId());

	}
	
	
	@Override
	public Integer updateAspirantes(Aspirantes aspirante) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("UPDATE ASPIRANTES SET NOMBREALUMNO = ?, EDAD = ?, FECHAINSCRIPCION = ?,"
				+ "CURSOID = ?, MAESTROID = ? WHERE IDALUMNO = ?"
				,new Object[] { aspirante.getNombreAlumno(),aspirante.getEdad(),aspirante.getFechaInscripcion(),
						aspirante.getCursoId(),aspirante.getMaestroId(),aspirante.getIdAlumno()
						
				}  );
	}
	
	@Override
	public Integer deleteAspirantes(Aspirantes aspirante) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("UPDATE ASPIRANTES SET STATUS = 0 WHERE IDALUMNO = ?", aspirante.getIdAlumno());
	}
	
	@Override
	public int[][] insertAspirantesMasivo(List<Aspirantes> aspirantes) {
		jdbcTemplate.setDataSource(getDataSource());
		
		int[][] updateCounts = null;
		try {
			
			updateCounts = jdbcTemplate.batchUpdate(
					"INSERT INTO ASPIRANTES (NOMBREALUMNO, EDAD, FECHAINSCRIPCION, CURSOID, MAESTROID) VALUES (?,?,SYSDATE,?,?)",
					aspirantes,
					100,
					new ParameterizedPreparedStatementSetter<Aspirantes>() {
						@Override
						public void setValues(PreparedStatement ps, Aspirantes argument) throws SQLException{
							ps.setString(1,argument.getNombreAlumno());
							ps.setInt(2, argument.getEdad());
//							ps.setDate(3,argument.getFechaInscripcion()); me marca error al asignar la fecha manual
							ps.setBigDecimal(3,argument.getCursoId());
							ps.setBigDecimal(4,argument.getMaestroId());
						}
					}
					);
			
		}catch(org.springframework.dao.DuplicateKeyException DKE) {
			System.out.println("EXCEPTION POR DATO DUPLICADO: "+DKE.getMessage());
		}
		
		return updateCounts;
	}

	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



}
