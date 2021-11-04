
package com.RitApp.web.servicios;

import java.awt.Image;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RitApp.web.entidades.Perfil;
import com.RitApp.web.entidades.Usuario;
import com.RitApp.web.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Transactional
	public void crearUsuario(String dni, String email, String contrasena, String nombre, String apellido, Date fechaNac,
			Integer edad, Integer telefono, String genero, String direccion,
			String pais , Perfil perfil, Image foto, File cv ) throws Exception {
		validar(dni, email, contrasena, nombre, apellido, fechaNac, edad, telefono, genero, direccion,
				pais/* , perfil */);
		Usuario usuario;
		usuario = new Usuario();

		usuario.setDni(dni);
		usuario.setEmail(email);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setFechaNacimiento(fechaNac);
		usuario.setEdad(edad);
		usuario.setTelefono(telefono);
		usuario.setGenero(genero);
		usuario.setDireccion(direccion);
		usuario.setPais(pais);
		/*
		 * usuario.setPerfil(perfil); usuario.setFoto(foto); usuario.setCv(cv);
		 */

		usuarioRepositorio.save(usuario);
	}

	@Transactional
	public void modificar(String dni, String email, String contraseña, String nombre, String apellido, Date fechaNac,
			Integer edad, Integer telefono, String genero, String direccion, String pais, Perfil perfil, Image foto,
			File cv) throws Exception {
		validar(dni, email, contraseña, nombre, apellido, fechaNac, edad, telefono, genero, direccion,
				pais/* , perfil */);
		Usuario usuario;
		usuario = buscarXId(dni);

		usuario.setEmail(email);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setFechaNacimiento(fechaNac);
		usuario.setEdad(edad);
		usuario.setTelefono(telefono);
		usuario.setGenero(genero);
		usuario.setDireccion(direccion);
		usuario.setPais(pais);
		usuario.setPerfil(perfil);
		/* usuario.setFoto(foto); */
		usuario.setCv(cv);

		usuarioRepositorio.save(usuario);
	}

	@Transactional
	public void eliminar(String dni) throws Exception {
		Usuario usuario = buscarXId(dni);
		usuarioRepositorio.delete(usuario);

	}

	public void validar(String dni, String email, String contraseña, String nombre, String apellido, Date fechaNac,
			Integer edad, Integer telefono, String genero, String direccion,
			String pais/* , Perfil perfil */)
			throws Exception {

		if (dni.isEmpty() || dni == null) {
			throw new Exception("el dni no pude ser nulo");
		}
		if (email.isEmpty() || email == null) {
			throw new Exception("el email no pude ser nulo");
		}
		if (contraseña.isEmpty() || contraseña == null) {
			throw new Exception("la contraseña no pude ser nula");
		}
		if (nombre.isEmpty() || nombre == null) {
			throw new Exception("el nombre no pude ser nulo");
		}
		if (apellido.isEmpty() || apellido == null) {
			throw new Exception("el apellido no pude ser nulo");
		}
		if (fechaNac == null) {
			throw new Exception("fechaNac no pude ser nula");
		}
		if (edad == null) {
			throw new Exception(" edad no pude ser nula");
		}
		if (telefono == null) {
			throw new Exception("el telefono no pude ser nulo");
		}
		if (genero.isEmpty() || genero == null) {
			throw new Exception("el genero no pude ser nulo");
		}
		if (direccion.isEmpty() || direccion == null) {
			throw new Exception("la direccion no pude ser nula");
		}
		if (pais.isEmpty() || pais == null) {
			throw new Exception("el pais no pude ser nulo");
		}
/*		}
		if (perfil == null) {
			throw new Exception("el perfil no pude ser nulo");
		}*/

	}

	public Usuario buscarXId(String dni) throws Exception {
		Optional<Usuario> respuesta = usuarioRepositorio.findById(dni);
		if (respuesta.isPresent()) {
			return respuesta.get();
		} else {
			throw new Exception("no se encuentra ningun Usuario con el id");
		}

	}

	public List<Usuario> listar() {
		return usuarioRepositorio.findAll();
	}
}
