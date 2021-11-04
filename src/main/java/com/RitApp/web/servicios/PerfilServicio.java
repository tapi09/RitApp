
package com.RitApp.web.servicios;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RitApp.web.entidades.Idioma;
import com.RitApp.web.entidades.Lenguaje;
import com.RitApp.web.entidades.Perfil;
import com.RitApp.web.repositorios.PerfilRepositorio;

@Service
public class PerfilServicio {

	@Autowired
	private PerfilRepositorio perfilRepositorio;

	@Transactional
	public void crearPerfil(String gitHubLink, String experienciaLaboral, List<Idioma> idiomas,
			String disponibilidadHoraria, String formacionAcademica, List<Lenguaje> lenguajes, String seniority,
			String estado, String trabajoActual) throws Exception {
		validar(gitHubLink, experienciaLaboral, idiomas, disponibilidadHoraria, formacionAcademica, lenguajes,
				seniority, estado, trabajoActual);
		Perfil perfil;
		perfil = new Perfil();

		perfil.setGitHubLink(gitHubLink);
		perfil.setExperienciaLaboral(experienciaLaboral);
		perfil.setIdiomas(idiomas);
		perfil.setDisponibilidadHoraria(disponibilidadHoraria);
		perfil.setFormacionAcademica(formacionAcademica);
		perfil.setLenguajes(lenguajes);
		perfil.setSeniority(seniority);
		perfil.setEstado(estado);
		perfil.setTrabajoActual(trabajoActual);

		perfilRepositorio.save(perfil);
	}

	public void validar(String gitHubLink, String experienciaLaboral, List<Idioma> idiomas,
			String disponibilidadHoraria, String formacionAcademica, List<Lenguaje> lenguajes, String seniority,
			String estado, String trabajoActual) throws Exception {

		if (gitHubLink == null) {
			throw new Exception("gitHubLink no pude ser nulo");
		}
		if (experienciaLaboral == null) {
			throw new Exception("experienciaLaboral no pude ser nula");
		}
		if (idiomas == null) {
			throw new Exception("la contraseña no pude ser nula");
		}
		if (disponibilidadHoraria == null) {
			throw new Exception("disponibilidadHoraria no pude ser nulo");
		}
		if (formacionAcademica == null) {
			throw new Exception("formacionAcademica no pude ser nulo");
		}
		if (lenguajes == null) {
			throw new Exception("lenguajes no pude ser nula");
		}
		if (seniority == null) {
			throw new Exception(" seniority no pude ser nula");
		}
		if (estado == null) {
			throw new Exception("estado no pude ser nulo");
		}

	}
	@Transactional
	public void modificar(String id, String gitHubLink, String experienciaLaboral, List<Idioma> idiomas,
			String disponibilidadHoraria, String formacionAcademica, List<Lenguaje> lenguajes, String seniority,
			String estado, String trabajoActual) throws Exception {
		validar(gitHubLink, experienciaLaboral, idiomas, disponibilidadHoraria, formacionAcademica, lenguajes,
				seniority, estado, trabajoActual);
		Perfil perfil;
		perfil =  buscarXId(id);

		
		perfil.setGitHubLink(gitHubLink);
		perfil.setExperienciaLaboral(experienciaLaboral);
		perfil.setIdiomas(idiomas);
		perfil.setDisponibilidadHoraria(disponibilidadHoraria);
		perfil.setFormacionAcademica(formacionAcademica);
		perfil.setLenguajes(lenguajes);
		perfil.setSeniority(seniority);
		perfil.setEstado(estado);
		perfil.setTrabajoActual(trabajoActual);

		perfilRepositorio.save(perfil);
	}
	
	@Transactional
	public Perfil buscarXId(String id)throws Exception{
			Optional<Perfil> respuesta = perfilRepositorio.findById(id);
			if (respuesta.isPresent()) {
				return respuesta.get();
			} else {
				throw new Exception("no se encuentra ningun Perfil con el id");
			}
	}
	@Transactional
	public void eliminar(String id)throws Exception{
		Perfil perfil = buscarXId(id);
		perfilRepositorio.delete(perfil);
	}
}
