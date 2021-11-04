package com.RitApp.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RitApp.web.entidades.Idioma;
import com.RitApp.web.entidades.Lenguaje;
import com.RitApp.web.entidades.Perfil;
import com.RitApp.web.servicios.PerfilServicio;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	PerfilServicio service;

	@GetMapping("/inicio")
	public String inicio() {
		return "inicio.html";
	}

	@PostMapping("/crear")
	public String crear(Model modelo, @RequestParam String gitHubLink, @RequestParam String experienciaLaboral,
			@RequestParam List<Idioma> idiomas, @RequestParam String disponibilidadHoraria, @RequestParam String formacionAcademica,
			@RequestParam List<Lenguaje> lenguajes, @RequestParam String seniority, @RequestParam String estado,
			@RequestParam String trabajoActual) throws Exception {
		try {
			service.crearPerfil(gitHubLink, experienciaLaboral, idiomas, disponibilidadHoraria, formacionAcademica, lenguajes, seniority, estado, trabajoActual);

		} catch (Exception e) {
			System.err.println("error " + e.getMessage());
			modelo.addAttribute("error ", e.getMessage());
			return "error.html";
		}
		return "redirect:/usuario/listar";
	}
	@PostMapping("/modificar")
	public String modificar(Model model, @RequestParam String id) throws Exception {
		try {
			Perfil perfil = new Perfil();
			if(id!=null) {
			perfil= service.buscarXId(id);
			}
			model.addAttribute("Perfil", perfil);
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			model.addAttribute("error ", e.getMessage());
			return "error.html";
		}
		return "redirect:";
	}
	@PostMapping("/eliminar")
	public String eliminar(Model model, @RequestParam String id) throws Exception {
		try {
			if(id==null) {
			System.out.println("id nulooo");
			}
			service.eliminar(id);
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());

		}
		return "redirect:";
	}
	
	

}
