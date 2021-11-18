package com.RitApp.web.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.RitApp.web.entidades.Perfil;
import com.RitApp.web.entidades.Trabajo;
import com.RitApp.web.repositorios.TrabajoRepositorio;

@Service
public class TrabajoServicio {
	@Autowired
	private TrabajoRepositorio trabajoRepositorio;

	@Autowired
	private PerfilServicio perfilServicio;
	@Autowired
	EmpresaServicio empresaServicio;

	// Crear trabajo (Empresa)
	public void crearTrabajo(Authentication usuario,String puesto, String zona, String modalidad, String lenguaje, String seniority,
			String idioma, String estudios, String algoSobreMi) throws Exception {
		validar(puesto, zona, modalidad);
		try {

			Trabajo trabajo = new Trabajo();
			trabajo.setPuesto(puesto);
			trabajo.setModalidad(modalidad);
			trabajo.setZona(zona);
			Perfil perfil = perfilServicio.crearPerfil(lenguaje, seniority, idioma, estudios, algoSobreMi);
			trabajo.setPerfil(perfil);
			setearEmpresa(usuario, trabajo);
			trabajoRepositorio.save(trabajo);

		} catch (Exception e) {
			throw new Exception("Error al crear trabajo");
		}
	}

	/*
	 * //Modificar trabajo (Empresa) public void modificarTrabajo(String id, String
	 * puesto, String lenguaje, String tipo, String tiempo) throws Exception{
	 * validar( puesto, tipo, tiempo, sueldo, lugar); try { Trabajo trabajo = new
	 * Trabajo();
	 * 
	 * trabajo.setId(id); trabajo.setPuesto(puesto); trabajo.setTipo(tipo);
	 * trabajo.setTiempo(tiempo); trabajo.setSueldo(sueldo);
	 * trabajo.setLugar(lugar);
	 * 
	 * trabajoRepositorio.save(trabajo);
	 * 
	 * } catch (Exception e) { throw new Exception("Error al modificar el trabajo");
	 * }
	 * 
	 * }
	 */

	// Eliminar trabajo (Empresa)
	public void eliminar(String id) throws Exception {
		Trabajo trabajo = buscarXId(id);
		trabajoRepositorio.delete(trabajo);

	}

	public Trabajo buscarXId(String id) throws Exception {
		Trabajo respuesta = trabajoRepositorio.buscarPorid(id);
		System.out.println("llegue");
		return respuesta;
	}

	public void validar(String puesto, String modalidad, String zona) throws Exception {
		try {

			if (puesto.isEmpty() || puesto == null) {
				throw new Exception("El campo 'puesto' no puede quedar en blanco!");
			}
			if (modalidad.isEmpty() || modalidad == null) {
				throw new Exception("El campo 'modalidad' no puede quedar en blanco!");
			}
			if (zona.isEmpty() || zona == null) {
				throw new Exception("El campo 'zona' no puede quedar en blanco!");
			}

		} catch (Exception e) {
			throw new Exception("Error en la validación de datos del trabajo");
		}

	}

	// Mostrar-Listar trabajos
	public List<Trabajo> listarTrabajos() {
		return trabajoRepositorio.findAll();
	}
	public void setearEmpresa(Authentication usuario,Trabajo trabajo) {
		trabajo.setEmpresa(empresaServicio.buscarxmail(usuario.getName()));
	}

}