package com.RitApp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RitApp.web.servicios.TrabajoServicio;

@Controller
@RequestMapping("/trabajo")
public class TrabajoController {

	@Autowired
	TrabajoServicio servicio;

	@GetMapping("/listarTrabajos")
	public String listar(Model model) throws Exception {
		
		
		  System.out.println("xxx"); model.addAttribute("trabajos",
		  servicio.listarTrabajos());
		 
	
		return "listarTrabajos.html";
	}

	@GetMapping("/crearTrabajo")
	public String creando() {
		return "trabajoCrear.html";
	}

	@PostMapping("/crearTrabajo")
	public String crear(Authentication usuario,Model modelo, @RequestParam String puesto, @RequestParam String zona,
			@RequestParam String lenguaje, @RequestParam String modalidad) throws Exception {
		
			servicio.crearTrabajo(usuario, puesto, zona, modalidad, lenguaje);

		
		return "redirect:/trabajo/listarTrabajos";
	}
	@GetMapping("/eliminarTrabajo")
	public String eliminar(@RequestParam String id) throws Exception {
		try {
			if (id != null) {
				servicio.eliminar(id);
			} else {
				System.out.println("tipo null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/trabajo/listar";

	}

	/*
	 * @PostMapping("/modificarTrabajo") public String modificarTrabajo(Model
	 * modelo, @RequestParam String id) throws Exception { try { Trabajo trabajo =
	 * new Trabajo(); if(id!=null) { trabajo= servicio.buscarXTipo(id); }
	 * modelo.addAttribute("trabajo", trabajo); } catch (Exception e) {
	 * System.out.println("error " + e.getMessage()); modelo.addAttribute("error ",
	 * e.getMessage()); return "error.html"; } return "modificarTrabajo.html"; }
	 */

}