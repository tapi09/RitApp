package com.RitApp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RitApp.web.servicios.EmparejadoService;

@Controller
@RequestMapping("/emparejado")
public class EmparejadoController {
	@Autowired
	EmparejadoService emparejadoService;
	@GetMapping("/listar")
	public String listar(Authentication usuario, Model modelo) {
		modelo.addAttribute("emparejados", emparejadoService.mostrarlikes(usuario.getName()));
		return "listar_like_empresa.html";
	}
	@PostMapping("/emparejarpostulante/{id}")
	public String emparejar(Authentication usuario, @RequestParam String id_trabajo) throws Exception {
		System.out.print("entre");
		emparejadoService.emparejarPostulante(usuario.getName(), id_trabajo);
		return "redirect:/";
	}
	@PostMapping("emparejarempresa/{id}")
	public String emparejarempresa(Authentication usuario, @RequestParam String id_emparejado) throws Exception {
		System.out.print("entre");
		emparejadoService.emparejarEmpresa(id_emparejado);
		return "redirect:/";
	}
}
