package com.RitApp.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.RitApp.web.servicios.UsuarioServicio;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {

	@Autowired
	UsuarioServicio usuarioServicio;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/", "/js/", "/img/*", "/**").permitAll().and().formLogin()
				.loginPage("/login").loginProcessingUrl("/logincheck").usernameParameter("email")
				.passwordParameter("password").defaultSuccessUrl("/pagina_inicio").failureUrl("/login?error=error")
				.permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll().and().csrf()
				.disable();
	}

}