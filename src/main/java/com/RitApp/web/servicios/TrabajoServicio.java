package com.RitApp.web.servicios;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RitApp.web.entidades.Trabajo;
import com.RitApp.web.repositorios.TrabajoRepositorio;

@Service
public class TrabajoServicio {
	@Autowired
	private TrabajoRepositorio trabajoRepositorio;

	// Crear trabajo (Empresa)
	public void crearTrabajo(String puesto, String lenguaje, String tipo, String tiempo) throws Exception {
		validar(puesto, lenguaje, tipo, tiempo);
		try {
			Trabajo trabajo = new Trabajo();

			trabajo.setPuesto(puesto);
			trabajo.setLenguaje(lenguaje);
			trabajo.setTipo(tipo);
			trabajo.setTiempo(tiempo);
			System.out.println("lcdsm");
			trabajoRepositorio.save(trabajo);
			System.out.println("LPM");

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

		Optional<Trabajo> respuesta = trabajoRepositorio.findById(id);
		if (respuesta.isPresent()) {
			return respuesta.get();
		} else {
			throw new Exception("no se encuentra ningun cliente con el id");
		}

	}

	/*
	 * //Buscar trabajo (Usuario) public Trabajo buscarXTipo(String tipo) throws
	 * Exception{
	 * 
	 * Optional<Trabajo> respuesta = trabajoRepositorio.findById(tipo); Trabajo
	 * trabajo; trabajo = buscarXTipo(tipo);
	 * 
	 * if (respuesta.isPresent()) { return respuesta.get(); } else { throw new
	 * Exception("No se encontraron empresas con el 'tipo' especificado"); }
	 * 
	 * }
	 */
	// Validar trabajo (DB)
	public void validar(String puesto, String lenguaje, String tipo, String tiempo) throws Exception {
		try {

			if (puesto.isEmpty() || puesto == null) {
				throw new Exception("El campo 'puesto' no puede quedar en blanco!");
			}
			if (tipo.isEmpty() || tipo == null) {
				throw new Exception("El campo 'tipo' no puede quedar en blanco!");
			}
			if (tiempo.isEmpty() || tiempo == null) {
				throw new Exception("El campo 'tiempo' no puede quedar en blanco!");
			}
			if (lenguaje.isEmpty() || lenguaje == null) {
				throw new Exception("El campo 'lenguaje' no puede quedar en blanco!");
			}

		} catch (Exception e) {
			throw new Exception("Error en la validación de datos del trabajo");
		}

	}

	// Mostrar-Listar trabajos
	public List<Trabajo> listarTrabajos() {
		return trabajoRepositorio.findAll();
	}

	/*
	 * //Buscar perfil()
	 * 
	 * List perfilBuscado = new ArrayList();
	 * 
	 * public Perfil buscarXExperiencia(String experienciaLaboral) throws Exception{
	 * List perfilBuscado = new ArrayList(); Optional<Perfil> respuesta =
	 * perfilRepositorio.findById(experienciaLaboral); Perfil perfil; perfil =
	 * buscarXExperiencia(experienciaLaboral);
	 * 
	 * perfilBuscado.add(perfil);
	 * 
	 * if (respuesta.isPresent()) { perfilBuscado.add(perfil); return
	 * respuesta.get(); } else { throw new
	 * Exception("No se encontraron perfiles con los años de 'experiencia' especificados"
	 * ); } }
	 * 
	 * //Mostrar perfil buscado public void listarPerfilBuscado(){ for (Object p:
	 * perfilBuscado) { System.out.println(p); } }
	 */
}