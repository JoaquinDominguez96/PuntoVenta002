package com.mx.proyecto.Config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration // indicar que es un archivo de configiracio
@EnableWebSecurity // Hablitar la seguridad del proyecto
@EnableGlobalMethodSecurity // Hablitar para todo el proyecto
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private DataSource datasource;
	
	@Override
	protected void configure (HttpSecurity http) throws Exception{
	http  //LOGIN VALIDO SIN DEJAR ACCEDER A OTRAS URLS SIN LOGGEARTE PREVIAMENTE Y VALIDA LA SEGURIDAD
	.csrf().disable()
	.authorizeRequests()
    .and()
    .formLogin()
    .loginPage("/LoginService").permitAll()
    .defaultSuccessUrl("/Inicio").failureUrl("/LoginService")
    .and()
    .authorizeRequests()
    .antMatchers("/Inicio").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
//    .antMatchers("/Venta").access("hasAnyRole('ROLE_USER')")
//    .antMatchers("/producto").access("hasAnyRole('ROLE_ADMIN')")
//    .antMatchers("/proveedores").access("hasAnyRole('ROLE_ADMIN')")
    .and()
    .logout()
    .logoutUrl("/logout")
    .invalidateHttpSession(true)
    .logoutSuccessHandler(logoutSuccessHandler())
    .deleteCookies("JSESSIONID")
    .and()
    .exceptionHandling().authenticationEntryPoint(null);
	}
	
	@Bean
	@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
    }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}
	
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		        auth.jdbcAuthentication()
		        .dataSource(datasource)
		        .usersByUsernameQuery("SELECT USERNAME, PASSWORD, CASE HABILITADO WHEN 'Y' THEN 1 ELSE 2 END AS HABILITADO\r\n"
		        		+ "		                FROM DB_JULIO23.ACCESO_USUARIOS  WHERE USERNAME=?")
		        .authoritiesByUsernameQuery("SELECT U.USERNAME, "
		        		+ "R.PERMISO FROM DB_JULIO23.ACCESO_USUARIOS U, DB_JULIO23.ROLES_USUARIOS R WHERE U.ROLE_USER = R.ID_PERMISO "
		        		+ "AND U.USERNAME=?");        
    }
	
	
	/*

	-->DIFERENTES TIPO DE AUTENTICACION: -> Spring Framework/Spring boot ->  Spring Security
	
	1.- TRABAJANDO -> Por medio de DB
	2.- LDAP -> por servicio externo
	3.- Servicios externos que los provee otras empresas...
	
	->Spring Framework
	/nombre proyecto/src/main/webapp/WEB-INF/views

	vistas/paginas -> codigo html
	Login.html -> Login.jsp (Java server pages)
	

	 */

}