package com.RitApp.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RitApp.web.entidades.Perfil;
import com.RitApp.web.enums.Rol;
import com.RitApp.web.servicios.UsuarioServicio;

@Controller
@RequestMapping("/")
public class PortalController {
	@Autowired
	UsuarioServicio usuarioServicio;

	@GetMapping("")
	public String inicio() {
		return "index.html";
	}

	@GetMapping("/login")
	public String login(HttpSession session, Authentication usuario, Model modelo,
			@RequestParam(required = false) String error) {
		if (error != null) {
			modelo.addAttribute("error", "Nombre de usuario o contraseña incorrecta");
		}
		return "login";
	}

	@GetMapping("/pagina_inicio")
	public String paginainicio(HttpSession session, Authentication usuario, Model modelo) throws Exception {
		modelo.addAttribute("mensaje", "Hola " + usuarioServicio.obtenernombre(usuario));
		modelo.addAttribute("rol", "Su rol es " + usuario.getAuthorities());
		return "/pagina_inicio";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, Authentication usuario, Model modelo) {
		return "login";
	}

	@GetMapping("/registro_postulante")
	public String registro_postulante() {
		return "registro_postulante";
	}

	@GetMapping("/registro_empresa")
	public String registro_empresa() {
		return "registro_empresa";
	}

	@GetMapping("/completar")
	public String completarperfil(Authentication usuario, Model model) {
		try {
			if (usuario.getAuthorities().contains(Rol.POSTULANTE)) {
				return "perfilpostulante";
			} else {
				return "perfilempresa";
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "/error";
		}
	}
}
