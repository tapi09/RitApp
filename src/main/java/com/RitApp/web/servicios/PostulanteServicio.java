
package com.RitApp.web.servicios;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.RitApp.web.entidades.Postulante;
import com.RitApp.web.enums.Rol;
import com.RitApp.web.repositorios.PostulanteRepositorio;

@Service
public class PostulanteServicio {

	@Autowired
	private PostulanteRepositorio postulanteRepositorio;

	@Transactional
	public void crearPostulante(String nombre, String apellido, String email, String contraseña, String contraseña1,
			Integer telefono) throws Exception {
		validar(nombre, apellido, email, contraseña, contraseña1, telefono);
		Postulante postulante;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		postulante = new Postulante();
		postulante.setEmail(email);
		postulante.setNombre(nombre);
		postulante.setApellido(apellido);
		postulante.setTelefono(telefono);
		postulante.setClave(encoder.encode(contraseña));
		postulante.setRol(Rol.POSTULANTE);

		postulanteRepositorio.save(postulante);
	}

	/*@Transactional
	public void modificar(String dni, String email, String contraseña, String nombre, String apellido, Date fechaNac,
			Integer edad, Integer telefono, String genero, String direccion, String pais) throws Exception {
		validar(dni, email, contraseña, nombre, apellido, fechaNac, edad, telefono, genero, direccion, pais);
		Postulante postulante;
		postulante = buscarXId(dni);

		postulante.setEmail(email);
		postulante.setNombre(nombre);
		postulante.setApellido(apellido);
		postulante.setFechaNacimiento(fechaNac);
		postulante.setEdad(edad);
		postulante.setTelefono(telefono);
		postulante.setGenero(genero);
		postulante.setDireccion(direccion);
		postulante.setPais(pais);

		postulanteRepositorio.save(postulante);
	}*/

	@Transactional
	public void eliminar(String id) throws Exception {
		Postulante postulante = buscarXId(id);
		postulanteRepositorio.delete(postulante);

	}

	public void validar(String nombre, String apellido, String email, String contraseña, String contraseña1, Integer telefono) throws Exception {

		if (email.isEmpty() || email == null) {
			throw new Exception("el email no pude ser nulo");
		}
		if (contraseña.isEmpty() || contraseña == null) {
			throw new Exception("la contraseña no pude ser nula");
			
		}
		if (!contraseña.equals(contraseña1)) {
			throw new Exception("las contraseñas deben coincidir");
			
		}
		if (nombre.isEmpty() || nombre == null) {
			throw new Exception("el nombre no pude ser nulo");
		}
		if (apellido.isEmpty() || apellido == null) {
			throw new Exception("el apellido no pude ser nulo");

		}
		if (telefono == null) {
			throw new Exception("el telefono no pude ser nulo");

		}
	}

	public Postulante buscarXId(String id) throws Exception {
		Optional<Postulante> respuesta = postulanteRepositorio.findById(id);
		if (respuesta.isPresent()) {
			return respuesta.get();
		} else {
			throw new Exception("no se encuentra ningun Postulante con el id");
		}
	}

	public List<Postulante> listar() {
		return postulanteRepositorio.findAll();
	}
}
