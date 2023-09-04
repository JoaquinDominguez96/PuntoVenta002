package com.mx.proyecto.RepositoryImplement;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mx.proyecto.Dto.MisEmpleadosDTO;
import com.mx.proyecto.Repository.MisEmpleadosDAO;
import com.mx.proyecto.entidad.MisEmpleados;

@SuppressWarnings("serial")
@Repository
public class MisEmpleadosDAOImpl extends GenericDAO<MisEmpleados, Long> implements MisEmpleadosDAO{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MisEmpleados> obtieneMisEmpleados() {
		
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(MisEmpleados.class);
		
		return (List<MisEmpleados>) criteria.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public Long obtenerValorSecuenciaTabla() {
		String sqlSequence = "SELECT SEC_MIS_EMPLEADOS.NEXTVAL AS SECUENCIAMISEMPLEADOS FROM DUAL";
		Session session = sessionFactory.getCurrentSession();
		SQLQuery quey = session.createSQLQuery(sqlSequence);
		List result = quey.list();
		return ((BigDecimal) result.get(0)).longValue();
	}

	 //CONSULTA PARA CONSULTAR 1 SOLO REGISTRO (NO LISTA) -> Select * from tabla where edad = ?
//	@Override
//	@SuppressWarnings("unchecked")// Quitar las advertencias (son las lineas amarillas)
//	@Transactional() // Es lo equivalente a un commit en oracle (Confirmar los cambios)
//	public MisEmpleados obtenerDatosPorActivo(long idMiEmpleado) {
//		
//		final Session session = sessionFactory.getCurrentSession(); // obt la session actual
//		final Criteria criteria = session.createCriteria(MisEmpleados.class); // select * from ESQUEMA.UsuariosAdmin
//		
//		criteria.add(Restrictions.eq("idMiEmpleado", idMiEmpleado)); // --> where edad = ?
//		
//		return (MisEmpleados) criteria.uniqueResult(); // Retornar un solo resultado
//	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MisEmpleados> obtieneMisEmpleadosMasculinos() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(MisEmpleados.class);
		criteria.add(Restrictions.eq("sexo", 'M'));
		
		return (List<MisEmpleados>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MisEmpleados> obtieneMisEmpleadosF35() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(MisEmpleados.class);
		criteria.add(Restrictions.eq("sexo", 'F'));
		criteria.add(Restrictions.eq("edad", 35));
		
		return (List<MisEmpleados>) criteria.list();
	}

	@Override
	@Transactional
	public MisEmpleados obtieneMisEmpleadosRFC(MisEmpleadosDTO nuevoEmpleadoRfc) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(MisEmpleados.class);
		criteria.add(Restrictions.eq("rfc", nuevoEmpleadoRfc.getRfc()));
		
		return (MisEmpleados) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public MisEmpleados obtieneMisEmpleadosRfcYCurp(MisEmpleadosDTO nuevoEmpleadoRfcYCurp) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(MisEmpleados.class);
		criteria.add(Restrictions.eq("rfc", nuevoEmpleadoRfcYCurp.getRfc()));
		criteria.add(Restrictions.eq("curp", nuevoEmpleadoRfcYCurp.getCurp()));
		
		return (MisEmpleados) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public MisEmpleados obtieneMisEmpleadosActivos(MisEmpleadosDTO MisEmpleadosActivos) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(MisEmpleados.class);
		criteria.add(Restrictions.eq("idMiEmpleado", MisEmpleadosActivos.getIdMiEmpleado()));
		criteria.add(Restrictions.eq("activo", 1));
		
		return (MisEmpleados) criteria.uniqueResult();
	}
	
	@Override
	public boolean validarCURP(String curp) {
		String expRegCurp = "^[A-Z]{4}\\d{6}[HM]{1}(AS|BC|BS|CC|CH|CL|CM|CS|DF|DG|GR|GT|HG|JC|MC|MN|MS|NE|NL|NT|OC|PL|QR|QT|SL|SP|SR|TC|TL|TS|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d]{1}\\d{1}$";

		if (curp.matches(expRegCurp)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean validarRFC(String rfc) {
		String expRegRfc = "^([A-ZÑ\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])([A-Z]|[0-9]){2}([A]|[0-9]){1})?$";

		if (rfc.matches(expRegRfc)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean validarNSS(String nss) {
		String expRegNum = "^[0-9]{10}$";

		if (nss.matches(expRegNum)) {
			return true;
		} else {
			return false;
		}
	}

	
} // fin de la clase
