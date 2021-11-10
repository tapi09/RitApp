package com.RitApp.web.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.RitApp.web.entidades.Postulante;
import com.RitApp.web.servicios.PostulanteServicio;

@Controller
@RequestMapping("/usuario")
public class PostulanteController {

	@Autowired
	PostulanteServicio service;
	@GetMapping("/listar")
	public String listar(Model model) throws Exception {
		try {

			model.addAttribute("usuarios", service.listar());
		} catch (Exception e) {
			System.err.println("error " + e.getMessage());
			model.addAttribute("error ", e.getMessage());
			return "error.html";

		}
		return "listarUsuarios.html";
	}
	@PostMapping("/generico")
	public String generico() {
	
		return "/login";
	}
	@PostMapping("/crear")
	public String crear(Model modelo, @RequestParam String apellido, @RequestParam String contraseña, 
			@RequestParam String direccion, @RequestParam String dni, @RequestParam Integer edad,
			@RequestParam String email, @RequestParam Date fechaNac, @RequestParam String genero, 
			@RequestParam String nombre, @RequestParam String pais, @RequestParam Integer telefono) throws Exception {
		try {
			service.crearUsuario(dni, email, contraseña, nombre, apellido, fechaNac, edad, telefono, genero, direccion, pais);

		} catch (Exception e) {
			System.err.println("error " + e.getMessage());
			modelo.addAttribute("error ", e.getMessage());
			return "error.html";
		}
		return "index.html";
	}
	@PostMapping("/modificar")
	public String modificar(Model modelo, @RequestParam String dni) throws Exception {
		try {
			Postulante postulante = new Postulante();
			if(dni!=null) {
			postulante= service.buscarXId(dni);
			}
			modelo.addAttribute("usuario", postulante);
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			modelo.addAttribute("error ", e.getMessage());
			return "error.html";
		}
		return "editarUsuario.html";
	}
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam String dni) throws Exception {
		try {
			if(dni!=null) {
				service.eliminar(dni);
			}else {
			System.out.println("dni null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/usuario/listar";

	}
	
}
