package com.mx.proyecto.RepositoryImplement;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mx.proyecto.Repository.UsuariosAdminDAO2;
import com.mx.proyecto.entidad.UsuariosAdmin;

@SuppressWarnings("serial")
@Repository
public class UsuariosAdminDAOImpl2 extends GenericDAO<UsuariosAdmin, Long> implements UsuariosAdminDAO2{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UsuariosAdmin> obtieneUsuarios() {
		
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(UsuariosAdmin.class);
		
		return (List<UsuariosAdmin>) criteria.list();
	}
	
	

	
} // fin de la clase
