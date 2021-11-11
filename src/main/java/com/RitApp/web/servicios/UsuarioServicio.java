package com.RitApp.web.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.RitApp.web.entidades.Usuario;
import com.RitApp.web.enums.Rol;
import com.RitApp.web.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService {
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
			if (usuario != null) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
				authorities.add(p);
				ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes();
				HttpSession session = attr.getRequest().getSession(true);

				return new User(usuario.getEmail(), usuario.getClave(), authorities);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public void guardarUser(String email, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setClave(encoder.encode(password));
		usuario.setRol(Rol.POSTULANTE);
		usuarioRepositorio.save(usuario);

	}
}
