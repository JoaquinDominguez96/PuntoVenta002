package com.mx.proyecto.RepositoryImplement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mx.proyecto.Repository.UsuariosAdminDAO;
import com.mx.proyecto.entidad.UsuariosAdmin;

@Repository
public class UsuariosAdminDAOImpl implements UsuariosAdminDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	// ------------   Constructor   --------------
	 public UsuariosAdminDAOImpl() {// -> Constructor vacio		 
	 }
	 public UsuariosAdminDAOImpl(SessionFactory sessionFactory) {// -> Constructor con un parametro
		 this.sessionFactory = sessionFactory;
	 }
	
	
	
	 // Consulta -> Hibernate SELECT * FROM TABLA;
	@Override
	@SuppressWarnings("unchecked")// Quitar las advertencias (son las lineas amarillas)
	@Transactional() // Es lo equivalente a un commit en oracle (Confirmar los cambios)
	public List<UsuariosAdmin> obtenerTodosLosDatos() {
		
		final Session session = sessionFactory.getCurrentSession(); // obt la session actual
		final Criteria criteria = session.createCriteria(UsuariosAdmin.class); 
		// criteria --> select * from + UsuariosAdmin -> select * from ESQUEMA.UsuariosAdmin
		
//		criteria.add(Restrictions.eq("rol", 3)); // --> where rol = 3
//		criteria.add(Restrictions.eq("edad", 37)); // --> where edad = 25
		
		return (List<UsuariosAdmin>) criteria.list();
	}
	
	
	// CONSULTA PARA CONSULTAR 1 SOLO REGISTRO (NO LISTA) -> Select * from tabla where edad = ?
//	@Override
//	@SuppressWarnings("unchecked")// Quitar las advertencias (son las lineas amarillas)
//	@Transactional() // Es lo equivalente a un commit en oracle (Confirmar los cambios)
//	public UsuariosAdmin obtenerDatosPorEdad(int edad) {
//		
//		final Session session = sessionFactory.getCurrentSession(); // obt la session actual
//		final Criteria criteria = session.createCriteria(UsuariosAdmin.class); // select * from ESQUEMA.UsuariosAdmin
//		
//		criteria.add(Restrictions.eq("edad", edad)); // --> where edad = ?
//		
//		return (UsuariosAdmin) criteria.uniqueResult(); // Retornar un solo resultado
//	}

	
	
	@Override
	@Transactional() // Es lo equivalente a un commit en oracle (Confirmar los cambios)
	public Integer insertarDatosHibernate(UsuariosAdmin datos) {
		
		sessionFactory.getCurrentSession().save(datos);// Esto es el insert a la tabla de USUARIOS_ADMIN
		
//		sessionFactory.getCurrentSession().saveOrUpdate(datos); 
		// Si existe informacion en la tabla simplem. UPDATE
		// Si NO existe informacion en la tabla SAVE-> REGISTRO
		
		return 1;
	}
	
	
	
	@Override
	@Transactional() // Es lo equivalente a un commit en oracle (Confirmar los cambios)
	public Integer eliminarUser(UsuariosAdmin datoEliminar) {
		
		sessionFactory.getCurrentSession().delete(datoEliminar); // delete
		
		return 1;
	}
	
	
	
	@Override
	@Transactional() // Es lo equivalente a un commit en oracle (Confirmar los cambios)
	public Integer actualizaInfo(UsuariosAdmin datos) {
		 
		sessionFactory.getCurrentSession().update(datos); // update
		
		return 1;
	}
	@Override
	public List<UsuariosAdmin> obtenerTodosLosDatosPorEdad(int edad) {
		final Session session = sessionFactory.getCurrentSession(); // obt la session actual
		final Criteria criteria = session.createCriteria(UsuariosAdmin.class); 
		criteria.add(Restrictions.eq("edad", edad));
		
		return (List<UsuariosAdmin>) criteria.list();
	}
	
	

	
} // fin de la clase
