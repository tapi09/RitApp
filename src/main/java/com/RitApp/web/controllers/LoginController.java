package com.RitApp.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping ("/pagina_inicio")
	public String paginainicio(HttpSession session, Authentication usuario,Model modelo) {
		modelo.addAttribute("mensaje", "Bienvenido "+usuario.getName());
		modelo.addAttribute("rol", "Su rol es "+usuario.getAuthorities().toString());
		return"/pagina_inicio";
	}
}