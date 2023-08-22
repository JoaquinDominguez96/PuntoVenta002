package com.mx.proyecto.RepositoryImplement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Mapper.MaestrosListMapper;
import com.mx.proyecto.Repository.IMaestrosRepository;

@Repository
public class MaestrosRepositoryImpl implements IMaestrosRepository {

	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Override
	public List<Maestros> getMaestros(){
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.query("SELECT * FROM MAESTROS WHERE STATUS = 1", new MaestrosListMapper<Maestros>());
		
//		return jdbcTemplate.query("SELECT A.IDALUMNO, A.NOMBREALUMNO, A.EDAD, A.FECHAINSCRIPCION, C.NOMBRE_CURSO, M.NOMBRE_MAESTRO FROM ASPIRANTES A JOIN CURSOS C ON A.CURSOID = C.CURSOID JOIN MAESTROS M ON A.MAESTROID = M.MAESTROID", new AspirantesListMapper<Aspirantes>());
	}

	@Override
	public Integer insertMaestros(Maestros nuevoMaestro) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("INSERT INTO MAESTROS (NOMBRE_MAESTRO, CORREO, TELEFONO)"
		+ "VALUES (?,?,?)", nuevoMaestro.getNombre_maestro(), nuevoMaestro.getCorreo(), nuevoMaestro.getTelefono());

	}
	
	
	@Override
	public Integer updateMaestros(Maestros maestro) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("UPDATE MAESTROS SET NOMBRE_MAESTRO = ?, CORREO = ?, TELEFONO = ? WHERE MAESTROID = ?"
				,new Object[] { maestro.getNombre_maestro(),maestro.getCorreo(),maestro.getTelefono(), maestro.getMaestroId()
						
				}  );
	}
	
	@Override
	public Integer deleteMaestros(Maestros maestro) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("UPDATE MAESTROS SET STATUS = 0 WHERE MAESTROID = ?", maestro.getMaestroId());
	}
	
	@Override
	public int[][] insertMaestrosMasivo(List<Maestros> maestros) {
		jdbcTemplate.setDataSource(getDataSource());
		
		int[][] updateCounts = null;
		try {
			
			updateCounts = jdbcTemplate.batchUpdate(
					"INSERT INTO MAESTROS (NOMBRE_MAESTRO , CORREO, TELEFONO) VALUES (?,?,?)",
					maestros,
					100,
					new ParameterizedPreparedStatementSetter<Maestros>() {
						@Override
						public void setValues(PreparedStatement ps, Maestros argument) throws SQLException{
							ps.setString(1,argument.getNombre_maestro());
							ps.setString(2, argument.getCorreo());
							ps.setLong(3,argument.getTelefono());
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