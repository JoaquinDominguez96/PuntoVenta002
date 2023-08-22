package com.mx.proyecto.Config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mx.proyecto.entidad.MisEmpleados;
import com.mx.proyecto.entidad.UsuariosAdmin;

@Configuration // para decirle que es de configuracion
@ComponentScan("com.mx.proyecto")//Mapear/buscar entres estos paquetes
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	
	private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true"); // Como esta en true lo que va a ser es cuando la consulta se ejecute se va a mostrar en la consola
//    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");// agregar el driver de oracle
    	return properties;
    }
  
  
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	sessionBuilder.addAnnotatedClasses(MisEmpleados.class);
    	sessionBuilder.addAnnotatedClasses(UsuariosAdmin.class); // Declarar todas clases Entitys para poder hacer las transacciones por Hibernate.
    	return sessionBuilder.buildSessionFactory();
    }
    
    
    @Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}
	
	

} // Fin de la clase