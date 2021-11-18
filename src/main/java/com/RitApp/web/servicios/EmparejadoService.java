package com.RitApp.web.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.RitApp.web.entidades.Emparejado;
import com.RitApp.web.entidades.Empresa;
import com.RitApp.web.entidades.Postulante;
import com.RitApp.web.entidades.Trabajo;
import com.RitApp.web.entidades.Usuario;
import com.RitApp.web.enums.Rol;
import com.RitApp.web.repositorios.EmparejadoRepositorio;

@Service
public class EmparejadoService {
	@Autowired
	EmparejadoRepositorio emparejadoRepositorio;
	@Autowired
	UsuarioServicio usuarioServicio;
	@Autowired
	TrabajoServicio trabajoServicio;
	@Autowired
	EmpresaServicio empresaServicio;
	@Autowired
	PostulanteServicio postulanteServicio;

	public void emparejarPostulante(String email, String id) throws Exception {
		Postulante postulante = new Postulante();
		Trabajo trabajo = new Trabajo();
		Empresa empresa = new Empresa();
		postulante = postulanteServicio.buscaxmail(email);
		trabajo = trabajoServicio.buscarXId(id);
		System.out.println(trabajo.getEmpresa().getId());
		empresa = trabajo.getEmpresa();
		Emparejado emparejado = new Emparejado();
		emparejado.setEmpresa(empresa);
		emparejado.setNombre_empresa(empresa.getNombre());
		emparejado.setPostulante(postulante);
		emparejado.setNombre_postulante(postulante.getNombre());
		emparejado.setTrabajo(trabajo);
		emparejado.setNombre_puesto(trabajo.getPuesto());
		emparejado.setEstado_postulante(true);
		emparejado.setEstado_empresa(false);
		emparejado.setEstado_bloqueado(true);
		emparejadoRepositorio.save(emparejado);
	}

	public void emparejarEmpresa(String emparejado_id) {
		Emparejado emparejado = new Emparejado();
		emparejado = emparejadoRepositorio.getById(emparejado_id);
		emparejado.setEstado_empresa(true);
		emparejadoRepositorio.save(emparejado);
	}

	public List<Emparejado> mostrarlikes(String email) {
		Usuario usuario = new Usuario();
		usuario = usuarioServicio.buscaruserxmail(email);
		if (usuario.getRol().equals(Rol.POSTULANTE)) {
			return mostraremparejadosxpostulante(email);
		} else {
			return mostraremparejadosxempresa(email);
		}
	}

	public List<Emparejado> mostraremparejadosxempresa(String email) {
		Empresa empresa = new Empresa();
		empresa = empresaServicio.buscarxmail(email);
		return emparejadoRepositorio.findByEmpresa(empresa);
	}

	public List<Emparejado> mostraremparejadosxpostulante(String email) {
		Postulante postulante = new Postulante();
		postulante = postulanteServicio.buscaxmail(email);
		return emparejadoRepositorio.findByPostulante(postulante);
	}
}
