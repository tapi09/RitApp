package com.RitApp.web.servicios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RitApp.web.entidades.Chat;
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
	@Autowired
	ChatServicio chatServicio;

	public void emparejarPostulante(String email, String id) throws Exception {
		Postulante postulante = new Postulante();
		Trabajo trabajo = new Trabajo();
		Empresa empresa = new Empresa();	
		Emparejado emparejado = new Emparejado();
		postulante = postulanteServicio.buscaxmail(email);
		trabajo = trabajoServicio.buscarXId(id);		
		if (comprobar(postulante, trabajo)) {
			System.out.println("ya existe");
		} else {
			System.out.println(trabajo.getEmpresa().getId());
			empresa = trabajo.getEmpresa();			
			emparejado.setEmpresa(empresa);
			emparejado.setNombre_empresa(empresa.getNombre());
			emparejado.setPostulante(postulante);
			emparejado.setNombre_postulante(postulante.getNombre());
			emparejado.setTrabajo(trabajo);
			emparejado.setNombre_puesto(trabajo.getPuesto());
			emparejado.setEstado_postulante(true);
			emparejado.setEstado_empresa(false);
			emparejado.setEstado_activo(false);
			emparejadoRepositorio.save(emparejado);
		}

	}

	public void emparejarEmpresa(String emparejado_id) {
		Emparejado emparejado = new Emparejado();
		emparejado = emparejadoRepositorio.getById(emparejado_id);
		emparejado.setEstado_empresa(true);
		emparejado.setEstado_activo(true);	
		Chat chat=new Chat();
		chat.setEmparejado(emparejado);
		chatServicio.guardar_chat(chat);
		emparejadoRepositorio.save(emparejado);
	}

	public List<Emparejado> mostrarlikesnuevos(String email) {
		List<Emparejado> listaEmparejados = new ArrayList<Emparejado>();
		List<Emparejado> listaEmparejadosanteriores = new ArrayList<Emparejado>();
		listaEmparejados = mostraremparejadosxempresa(email);
		for (Emparejado emparejado : listaEmparejados) {
			if (emparejado.getEstado_empresa()) {
				listaEmparejadosanteriores.add(emparejado);
				System.out.println(emparejado.getEstado_empresa());
			}
		}
		listaEmparejados.removeAll(listaEmparejadosanteriores);
		return listaEmparejados;
	}

	public List<Emparejado> mostrarlikes(String email) {
		Usuario usuario = new Usuario();
		usuario = usuarioServicio.buscaruserxmail(email);
		if (usuario.getRol().equals(Rol.POSTULANTE)) {
			return mostraremparejadosxpostulante(email);
		} else {
			return mostrarlikesnuevos(email);
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

	public Boolean comprobar(Postulante postulante, Trabajo trabajo) {
		if (!emparejadoRepositorio.findByPostulanteAndTrabajo(postulante, trabajo).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public List<Emparejado> mostrarlikeactivos(String email) {
		List<Emparejado> listaEmparejados = new ArrayList<Emparejado>();
		List<Emparejado> listaEmparejadosactivos = new ArrayList<Emparejado>();
		listaEmparejados = mostraremparejadosxempresa(email);
		for (Emparejado emparejado : listaEmparejados) {
			if (emparejado.getEstado_activo()) {
				listaEmparejadosactivos.add(emparejado);
				System.out.println(emparejado.getEstado_empresa());
			}
		}
		return listaEmparejadosactivos;
	}

}
