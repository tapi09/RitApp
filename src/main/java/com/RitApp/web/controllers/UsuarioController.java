package com.RitApp.web.controllers;

import java.awt.Image;
import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RitApp.web.entidades.Perfil;
import com.RitApp.web.entidades.Usuario;
import com.RitApp.web.servicios.UsuarioServicio;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioServicio service;

	@GetMapping("/inicio")
	public String inicio() {
		return "crearUsuario.html";
	}

	@GetMapping("/listar")
	public String listar(Model model) throws Exception {
		try {

			model.addAttribute("usuarios", service.listar());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "index.html";

		}
		return "listarUsuarios.html";
	}

	@PostMapping("/crear")
	public String crear(@RequestParam String apellido, @RequestParam String contrasena,

			@RequestParam String direccion, @RequestParam String dni, @RequestParam Integer edad,

			@RequestParam String email, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNac,

			@RequestParam String genero, @RequestParam String nombre, @RequestParam String pais,

			@RequestParam(required = false) Perfil perfil,

			@RequestParam Integer telefono, @RequestParam(required = false) Image foto,
			@RequestParam(required = false) File cv) throws Exception {
		try {
			System.out.println("asd");

			service.crearUsuario(dni, email, contrasena, nombre, apellido, fechaNac, edad, telefono, genero, direccion,
					pais, perfil, foto, cv);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "index.html";
		}
		return "index.html";
	}

	@PostMapping("/modificar")
	public String modificar(Model modelo, @RequestParam String dni) throws Exception {
		try {
			Usuario usuario = new Usuario();
			if (dni != null) {
				usuario = service.buscarXId(dni);
			}
			modelo.addAttribute("usuario", usuario);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "index.html";

		}
		return "editarUsuario.html";
	}

	@GetMapping("/eliminar")
	public String eliminar(@RequestParam String dni) throws Exception {
		try {
			if (dni != null) {
				service.eliminar(dni);
			} else {
				System.out.println("dni null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/usuario/listar";

	}

}
