package com.mx.proyecto.RepositoryImplement;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.TimestampType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.mx.proyecto.Repository.DAO;

public abstract class GenericDAO<T, PK extends Serializable> implements DAO<T, PK>{
	
	 @Autowired
	   protected SessionFactory sessionFactory;

	   protected final Class<T> entityType;

	   {
	      final ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
	      final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
	      entityType = (Class) actualTypeArguments[0];
	   }

	   @Override
	   @Transactional(propagation = Propagation.REQUIRED)
	   public PK create(final T newInstance) {

	      final Session session = sessionFactory.getCurrentSession();

	      final PK pk = (PK) session.save(newInstance);

	      session.flush();

	      return pk;
	   }

	   @Override
	   @Transactional(propagation = Propagation.REQUIRED)
	   public T read(final PK id) {
	      return (T) sessionFactory.getCurrentSession().get(entityType, id);
	   }

	   @Override
	   @Transactional(propagation = Propagation.REQUIRED)
	   public void update(final T transientObject) {
	      final Session session = sessionFactory.getCurrentSession();
	      session.update(transientObject);
	      session.flush();
	   }

	   @Override
	   @Transactional(propagation = Propagation.REQUIRED)
	   public void saveOrUpdate(final T transientObject) {
	      final Session session = sessionFactory.getCurrentSession();
	      session.saveOrUpdate(transientObject);
	      session.flush();
	   }

	   @Override
	   @Transactional(propagation = Propagation.REQUIRED)
	   public void delete(final PK persistentObjectId) {
	      final Session session = sessionFactory.getCurrentSession();
	      session.delete(read(persistentObjectId));
	      session.flush();
	   }

	   @Override
	   @Transactional(propagation = Propagation.REQUIRED)
	   public void updateWithNoFlush(final T transientObject) {
	      final Session session = sessionFactory.getCurrentSession();
	      session.update(transientObject);
	   }

	   @Override
	   @Transactional(propagation = Propagation.REQUIRED)
	   public void flush() {
	      sessionFactory.getCurrentSession().flush();
	   }

	   public Timestamp obtenerMomentoActual() {
	      final Session session = sessionFactory.getCurrentSession();
	      SQLQuery query = session.createSQLQuery("select systimestamp param from dual");
	      query.addScalar("param", TimestampType.INSTANCE);
	      Timestamp ahora = (Timestamp) query.uniqueResult();
	      return ahora;
	   }


}