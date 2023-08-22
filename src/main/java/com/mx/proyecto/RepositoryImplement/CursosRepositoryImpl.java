package com.mx.proyecto.RepositoryImplement;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Mapper.CursosListMapper;
import com.mx.proyecto.Mapper.ValidaCursoListMapper;
import com.mx.proyecto.Repository.ICursosRepository;

@Repository
public class CursosRepositoryImpl implements ICursosRepository {

	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Override
	public List<Cursos> getCursos(){
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.query("SELECT * FROM CURSOS WHERE STATUS = 1", new CursosListMapper<Cursos>());
		
	}
	@Override
	public List<String> getNomCursos(){
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate.queryForList("SELECT NOMBRE_CURSO FROM CURSOS", String.class);
		
	}

	@Override
	public Integer insertCursos(Cursos nuevoCurso) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("INSERT INTO CURSOS (NOMBRE_CURSO, HORAS, ESPECIALIDAD)"
		+ "VALUES (?,?,?)", nuevoCurso.getNombre_Curso(), nuevoCurso.getHoras(), nuevoCurso.getEspecialidad());

	}
	
	@Override
	public Integer updateCursos(Cursos curso) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("UPDATE CURSOS SET NOMBRE_CURSO = ?, HORAS = ?, ESPECIALIDAD = ? WHERE CURSOID = ?"
				,new Object[] { curso.getNombre_Curso(),curso.getHoras(),curso.getEspecialidad(), curso.getCursoId()
						
				}  );
	}
	
	@Override
	public Integer deleteCursos(Cursos curso) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("UPDATE CURSOS SET STATUS = 0 WHERE CURSOID = ?", curso.getCursoId());
	}
	
	@Override
	public int[][] insertCursosMasivo(List<Cursos> cursos) {
		jdbcTemplate.setDataSource(getDataSource());
		
		int[][] updateCounts = null;
		try {
			
			updateCounts = jdbcTemplate.batchUpdate(
					"INSERT INTO CURSOS (NOMBRE_CURSO, HORAS, ESPECIALIDAD) VALUES (?,?,?)",
					cursos,
					100,
					new ParameterizedPreparedStatementSetter<Cursos>() {
						@Override
						public void setValues(PreparedStatement ps, Cursos argument) throws SQLException{
							ps.setString(1,argument.getNombre_Curso());
							ps.setInt(2, argument.getHoras());
							ps.setString(3,argument.getEspecialidad());
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