package pe.idat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pe.idat.service.UserServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserServiceImpl userServiceImpl;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(userServiceImpl);
		
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeRequests()
		    .antMatchers("/peliculas/listar").permitAll()
		    .antMatchers("/clasificaciones/listar").permitAll()
		    .antMatchers("/pelicula_genero/listar").permitAll()
		    
		    .antMatchers("/peliculas/registrar").access("hasRole('SUPERVISOR')")
		    .antMatchers("/peliculas/editar/*").access("hasRole('SUPERVISOR')")
		    .antMatchers("/peliculas/borrar/*").access("hasRole('SUPERVISOR')")
		    
		    .antMatchers("/clasificaciones/registrar").access("hasRole('SUPERVISOR')")
		    .antMatchers("/clasificaciones/editar/*").access("hasRole('SUPERVISOR')")
		    .antMatchers("/clasificaciones/borrar/*").access("hasRole('SUPERVISOR')")

		    .antMatchers("/proyecciones/listar").access("hasRole('CLIENTE')")
		    .antMatchers("/proyecciones/registrar").access("hasRole('SUPERVISOR')")
		    .antMatchers("/proyecciones/editar/*").access("hasRole('SUPERVISOR')")
		    .antMatchers("/proyecciones/borrar/*").access("hasRole('SUPERVISOR')")

		    .antMatchers("/pelicula_genero/listar2").access("hasRole('CLIENTE')")
		    .antMatchers("/pelicula_genero/registrar").access("hasRole('SUPERVISOR')")
		    .antMatchers("/pelicula_genero/editar/*").access("hasRole('SUPERVISOR')")
		    .antMatchers("/pelicula_genero/borrar/*").access("hasRole('SUPERVISOR')")
		    
		    .antMatchers("/administradores/listar").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/administradores/registrar").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/administradores/editar/*").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/administradores/borrar/*").access("hasRole('ADMINISTRADOR')")
		    
		    .antMatchers("/generos/listar").access("hasRole('CLIENTE')")
		    .antMatchers("/generos/registrar").access("hasRole('SUPERVISOR')")
		    .antMatchers("/generos/editar/*").access("hasRole('SUPERVISOR')")
		    .antMatchers("/generos/borrar/*").access("hasRole('ADMINISTRADOR')")
		    
		    .antMatchers("/cines/listar").access("hasAnyRole('CLIENTE', 'SUPERVISOR', 'ADMINISTRADOR')")
		    .antMatchers("/cines/registrar").access("hasAnyRole('SUPERVISOR', 'ADMINISTRADOR')")
		    .antMatchers("/cines/borrar/*").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/cines/editar/*").access("hasRole('ADMINISTRADOR')")
		    
		    .antMatchers("/generos/listar").access("hasRole('CLIENTE')")
		    .antMatchers("/generos/registrar").access("hasRole('SUPERVISOR')")
		    .antMatchers("/generos/editar/*").access("hasRole('SUPERVISOR')")
		    .antMatchers("/generos/borrar/*").access("hasRole('ADMINISTRADOR')")
		    
		    .antMatchers("/roles/listar").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/roles/registrar").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/roles/editar/*").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/roles/borrar/*").access("hasRole('ADMINISTRADOR')")
		    
		    .antMatchers("/usuarios/listar").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/usuarios/registrar").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/usuarios/editar/*").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/usuarios/borrar/*").access("hasRole('ADMINISTRADOR')")
		    
		    .antMatchers("/usuarios_roles/listar").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/usuarios_roles/listar2").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/usuarios_roles/registrar").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/usuarios_roles/editar/*/*").access("hasRole('ADMINISTRADOR')")
		    .antMatchers("/usuarios_roles/borrar/*/*").access("hasRole('ADMINISTRADOR')")
		    ;
		
		http.authorizeRequests().and()
		    .httpBasic();

		http.authorizeRequests().and()
	        .csrf().disable();

		http.authorizeRequests().and()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
