
package com.RitApp.web.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.RitApp.web.entidades.Empresa;
import com.RitApp.web.entidades.Postulante;
import com.RitApp.web.entidades.Trabajo;
import com.RitApp.web.entidades.Usuario;
import com.RitApp.web.enums.Rol;
import com.RitApp.web.repositorios.PostulanteRepositorio;
import com.RitApp.web.repositorios.UsuarioRepositorio;

@Service
public class PostulanteServicio {

	@Autowired
	private PostulanteRepositorio postulanteRepositorio;
	@Autowired
	private UsuarioServicio usuarioServicio;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

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
	public void sumarlike_postulante(Empresa empresa, Postulante postulante) {
		List<Empresa> lista_votos_actualizada= new ArrayList<>();
		lista_votos_actualizada=postulante.getLikeDeEmpresas();
		lista_votos_actualizada.add(empresa);
		postulante.setLikeDeEmpresas(lista_votos_actualizada);
		postulanteRepositorio.save(postulante);
	}
	public void sumarlike_trabajo(Postulante postulante, Trabajo trabajo) {
		List<Trabajo> lista_votos_actualizada= new ArrayList<>();
		lista_votos_actualizada=postulante.getLikeatrabajos();
		lista_votos_actualizada.add(trabajo);
		postulante.setLikeatrabajos(lista_votos_actualizada);
		postulanteRepositorio.save(postulante);
	}
	public void comprobarmatch(Postulante postulante) {
		ArrayList<String> lista_id_empresa_trabajos= new ArrayList<String>();
		ArrayList<String> lista_id_empresas= new ArrayList<String>();
		ArrayList<String> lista_de_Match= new ArrayList<String>();
		postulante.getLikeDeEmpresas();
	for(Empresa empresa : postulante.getLikeDeEmpresas() ) {
		lista_id_empresas.add(empresa.getId());
	}
	for(Trabajo trabajo: postulante.getLikeatrabajos()) {
		lista_id_empresa_trabajos.add(trabajo.getEmpresa().getId());
	}
	for (String id:lista_id_empresa_trabajos) {
		if(lista_id_empresas.contains(id)) {
			lista_de_Match.add("empresa "+id);
		}
	}
	postulante.setListaMatch(lista_de_Match);		
	}
	public Postulante buscaxmail(String email) {
		Usuario usuario=new Usuario();
		usuario=usuarioServicio.buscaruserxmail(email);
		
		return postulanteRepositorio.getById(usuario.getId());
	}
}
