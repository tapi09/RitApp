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
	@GetMapping("")
	public String login(Model modelo,@RequestParam(required=false) String error,@RequestParam(required=false) String email) {		
		if (error!= null) {
			modelo.addAttribute("error", "El usuario o la contraseña son incorrectos");
		}
		if (email!= null) {
			modelo.addAttribute("username", email);
		}
	return null;
	}
	@GetMapping("/loginsuccess")
	public String loginresolver() {
				
		return "redirect:/";
}
}