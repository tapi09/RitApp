
package com.RitApp.web.servicios;

import java.util.Date;
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
	public void crearUsuario(String dni, String email, String contraseña, String nombre, String apellido, Date fechaNac,
			Integer edad, Integer telefono, String genero, String direccion, String pais)
			throws Exception {
		validar(dni, email, contraseña, nombre, apellido, fechaNac, edad, telefono, genero, direccion, pais);
		Postulante postulante;
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		postulante = new Postulante();		
		postulante.setDni(dni);
		postulante.setEmail(email);
		postulante.setNombre(nombre);
		postulante.setApellido(apellido);
		postulante.setFechaNacimiento(fechaNac);
		postulante.setEdad(edad);
		postulante.setTelefono(telefono);
		postulante.setGenero(genero);
		postulante.setDireccion(direccion);
		postulante.setPais(pais);
		postulante.setClave(encoder.encode(contraseña));
		postulante.setRol(Rol.POSTULANTE);

		postulanteRepositorio.save(postulante);
	}
	
	@Transactional
	public void modificar(String dni, String email, String contraseña, String nombre, String apellido, Date fechaNac,
			Integer edad, Integer telefono, String genero, String direccion, String pais)
			throws Exception {
		validar(dni, email, contraseña, nombre, apellido, fechaNac, edad, telefono, genero, direccion, pais);
		Postulante postulante;
		postulante =  buscarXId(dni);

		
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
	}
	@Transactional
	public void eliminar(String dni) throws Exception {
		Postulante postulante = buscarXId(dni);
			postulanteRepositorio.delete(postulante);
		
	}
	
	
	

	public void validar(String dni, String email, String contraseña, String nombre, String apellido, Date fechaNac,
			Integer edad, Integer telefono, String genero, String direccion, String pais)
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
	}
	
	public Postulante buscarXId(String dni)throws Exception{
		Optional<Postulante> respuesta = postulanteRepositorio.findById(dni);
		if (respuesta.isPresent()) {
			return respuesta.get();
		} else {
			throw new Exception("no se encuentra ningun Postulante con el id");
		}
	}
	public List<Postulante> listar(){
		return postulanteRepositorio.findAll();
	}
}
