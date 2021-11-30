package com.RitApp.web.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.RitApp.web.entidades.Emparejado;
import com.RitApp.web.entidades.Perfil;
import com.RitApp.web.entidades.Trabajo;
import com.RitApp.web.entidades.Usuario;
import com.RitApp.web.enums.Rol;
import com.RitApp.web.repositorios.EmparejadoRepositorio;
import com.RitApp.web.repositorios.TrabajoRepositorio;

@Service
public class TrabajoServicio {
	@Autowired
	private TrabajoRepositorio trabajoRepositorio;

	@Autowired
	private PerfilServicio perfilServicio;
	@Autowired
	EmpresaServicio empresaServicio;
	@Autowired
	EmparejadoService emparejadoService;
	@Autowired
	UsuarioServicio usuarioServicio;

	// Crear trabajo (Empresa)
	public void crearTrabajo(Authentication usuario, String puesto, String zona, String modalidad, String lenguaje)
			throws Exception {
		validar(puesto, zona, modalidad);
		try {

			Trabajo trabajo = new Trabajo();
			trabajo.setPuesto(puesto);
			trabajo.setModalidad(modalidad);
			trabajo.setZona(zona);
			trabajo.setLenguajes(lenguaje);
			setearEmpresa(usuario, trabajo);
			trabajo.setNombre_empresa(trabajo.getEmpresa().getNombre());
			trabajoRepositorio.save(trabajo);

		} catch (Exception e) {
			throw new Exception("Error al crear trabajo");
		}
	}

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
	public List<Trabajo> listarTrabajos(String email) {
		List<Trabajo> lista_finalList = new ArrayList<Trabajo>();
		lista_finalList = trabajoRepositorio.findAll();
		System.out.println("entro1");
		Usuario usuario= new Usuario();
		usuario=usuarioServicio.buscaruserxmail(email);				
		if (usuario.getRol().equals(Rol.POSTULANTE)) {
			List<Emparejado> lista_like_postulante = new ArrayList<Emparejado>();
			lista_like_postulante = emparejadoService.mostrarlikes(usuario.getEmail());
			System.out.println("entro");
			for (Emparejado emparejado : lista_like_postulante) {
				if (lista_finalList.contains(emparejado.getTrabajo())) {
					System.out.println("entro"+emparejado.getNombre_puesto());
					lista_finalList.remove(emparejado.getTrabajo());
				}				
			}
		}
		return lista_finalList;
	}

	public void setearEmpresa(Authentication usuario, Trabajo trabajo) {
		trabajo.setEmpresa(empresaServicio.buscarxmail(usuario.getName()));
	}

}