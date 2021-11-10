package com.RitApp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.RitApp.web.servicios.EmpresaServicio;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
	@Autowired
	EmpresaServicio empresaServicio;

	
    @PostMapping("/registrar")
    public String FormularioCrear(ModelMap model,@RequestParam String nombre,@RequestParam String actividad,@RequestParam String password
    		,@RequestParam String password1,@RequestParam String email ) throws Exception {
    	
    	empresaServicio.crearEmpresa(email, password, password1, nombre, actividad, " "," ", " ", " ");
    	return"redirect:/";
    }
}
