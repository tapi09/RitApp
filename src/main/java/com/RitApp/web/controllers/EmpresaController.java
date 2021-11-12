package com.RitApp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RitApp.web.servicios.EmpresaServicio;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
	@Autowired
	EmpresaServicio empresaServicio;
    @GetMapping("/registrar")
    public String crear(){
    	
    	return "registro_empresa";
    }

    @PostMapping("/registrar")
    public String crear(@RequestParam String nombre,@RequestParam String actividad,@RequestParam String email,@RequestParam String password,@RequestParam String password1) throws Exception {
    	empresaServicio.crearEmpresa(email, password, password1, nombre, actividad);
    	return "redirect:/";
    }


}