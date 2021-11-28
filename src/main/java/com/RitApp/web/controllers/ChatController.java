package com.RitApp.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RitApp.web.entidades.Chat;
import com.RitApp.web.servicios.ChatServicio;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	ChatServicio chatServicio;

	@PostMapping("/mostrarchat")
	public String mostrarchat(Authentication usuario, Model modelo, @RequestParam String id_emparejado) {
		System.out.println("mostrarchat");
		List<String> lista_a_mostrar = new ArrayList<String>();
		Chat chat = new Chat();
		chat = chatServicio.buscarchatxEmparejado(id_emparejado);
		lista_a_mostrar = chatServicio.mostrar_mensajes(chat);
		modelo.addAttribute("mensajes", lista_a_mostrar);
		modelo.addAttribute("id_emparejado", chat.getEmparejado().getId());
		modelo.addAttribute("id_chat", chat.getId());
		return "listarchat.html";
	}

	@PostMapping("/enviarmensaje")
	public String enviarmensaje(Authentication usuario, Model modelo, @RequestParam String id_emparejado,
			@RequestParam String mensajeenviado) {
		System.out.println("entro");
		List<String> lista_nueva = new ArrayList<String>();
		Chat chat = new Chat();
		System.out.println(mensajeenviado);
		chat = chatServicio.buscarchatxEmparejado(id_emparejado);
		lista_nueva = chatServicio.mostrar_mensajes(chat);
		lista_nueva.add(mensajeenviado);
		chat.setMensajes(lista_nueva);
		chatServicio.guardar_chat(chat);
		modelo.addAttribute("mensajes", lista_nueva);
		modelo.addAttribute("id_emparejado", chat.getEmparejado().getId());
		modelo.addAttribute("id_chat", chat.getId());

		return "listarchat.html";
	}

}
