package com.RitApp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RitApp.web.entidades.Chat;
import com.RitApp.web.error.MyException;
import com.RitApp.web.servicios.ChatServicio;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	ChatServicio chatServicio;

	@PostMapping("/mostrarchat")
	public String mostrarchat(Authentication usuario, Model modelo, @RequestParam String id_emparejado) {
		System.out.println("mostrarchat");
		try {			
			Chat chat = new Chat();
			chat = chatServicio.buscarchatxEmparejado(id_emparejado);
			modelo = chatServicio.add_attibutes(modelo, usuario, chat);
		} catch (MyException e) {
			System.err.println("Ocurrió un error" + " mostrar chat");
			modelo.addAttribute("error", e.getMessage());
			return "/error";
		}
		return "listarchat.html";
	}

	@PostMapping("/enviarmensaje")
	public String enviarmensaje(Authentication usuario, Model modelo, @RequestParam String id_chat,
			@RequestParam String textoenviado) {
		try {
			Chat chat = chatServicio.buscarchatxid(id_chat);
			chatServicio.enviar_mensaje(chat, usuario, textoenviado);
			modelo = chatServicio.add_attibutes(modelo, usuario, chat);
		} catch (MyException e) {
			System.err.println("Ocurrió un error" + " no se pudo enviar mensaje");
			modelo.addAttribute("error", e.getMessage() + "no se pudo enviar el mensaje");
			return "/error";
		}
		return "listarchat.html";
	}

	@GetMapping("/actualizar")
	public String actualizar(Authentication usuario, Model modelo, @RequestParam String id_chat) {
		try {
			Chat chat = chatServicio.buscarchatxid(id_chat);			
			modelo = chatServicio.add_attibutes(modelo, usuario, chat);
			return "listarchat.html :: #chat";

		} catch (MyException e) {
			modelo.addAttribute("eeror", e.getMessage());
			return "/error";
		}
	}

	@GetMapping("/estado")
	public ResponseEntity<String> estado(Authentication usuario, Model modelo, @RequestParam String id_chat,
			@RequestParam String id_listamensajes) throws MyException {
		Chat chat = new Chat();
		chat = chatServicio.buscarxid(id_chat);
		if (chat.getId_listamensajes().equals(id_listamensajes)) {
			System.out.println("actualizado");
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			System.out.println(
					"desactualizado" + "lista html:" + id_listamensajes + " lista base: " + chat.getId_listamensajes());
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}
