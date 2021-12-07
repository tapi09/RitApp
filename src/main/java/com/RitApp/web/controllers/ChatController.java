package com.RitApp.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String mostrarchat(Authentication usuario, Model modelo, @RequestParam String id_emparejado)
			throws Exception {
		System.out.println("mostrarchat");
		try {
			String tipologeado = chatServicio.Tipologeado(usuario);
			System.out.println(tipologeado);
			Chat chat = new Chat();
			chat = chatServicio.buscarchatxEmparejado(id_emparejado);
			if (tipologeado.equals("POSTULANTE")) {
				modelo.addAttribute("destinatario", chat.getEmparejado().getEmpresa());
			} else {
				modelo.addAttribute("destinatario", chat.getEmparejado().getPostulante());
			}
			modelo.addAttribute("tipologeado", tipologeado);
			modelo.addAttribute("mensajes", chatServicio.listadeMensajes(chat.getId()));
			modelo.addAttribute("id_emparejado", chat.getEmparejado().getId());
			modelo.addAttribute("id_chat", chat.getId());
		} catch (Exception e) {
			System.err.println("Ocurrió un error" + " mostrar chat");
			modelo.addAttribute("error", e.getMessage());
			return "error.html";
		}
		return "listarchat.html";
	}

	@PostMapping("/enviarmensaje")
	public String enviarmensaje(Authentication usuario, Model modelo, @RequestParam String id_emparejado,
			@RequestParam String mensajeenviado) throws Exception {
		System.out.println("entro");
		try {
			String mensaje_correjido = mensajeenviado.replaceAll("\n", "<br/>");
			String tipologeado = chatServicio.Tipologeado(usuario);
			Chat chat = new Chat();
			chat = chatServicio.buscarchatxEmparejado(id_emparejado);
			chatServicio.enviar_mensaje(chat, usuario, mensaje_correjido);
			if (tipologeado.equals("POSTULANTE")) {
				modelo.addAttribute("destinatario", chat.getEmparejado().getEmpresa());
			} else {
				modelo.addAttribute("destinatario", chat.getEmparejado().getPostulante());
			}
			modelo.addAttribute("tipologeado", tipologeado);
			modelo.addAttribute("mensajes", chatServicio.listadeMensajes(chat.getId()));
			modelo.addAttribute("id_emparejado", chat.getEmparejado().getId());
			modelo.addAttribute("id_chat", chat.getId());
		} catch (Exception e) {
			System.err.println("Ocurrió un error" + " no se pudo enviar mensaje");
			modelo.addAttribute("error", e.getMessage() + "no se pudo enviar el mensaje");
			return "error.html";
		}
		return "listarchat.html";
	}

	@GetMapping("/actualizar")
	public String actualizar(Authentication usuario, Model modelo, @RequestParam String id_emparejado) {
		String tipologeado = chatServicio.Tipologeado(usuario);
		Chat chat = new Chat();
		chat = chatServicio.buscarchatxEmparejado(id_emparejado);
		modelo.addAttribute("tipologeado", tipologeado);
		modelo.addAttribute("mensajes", chatServicio.listadeMensajes(chat.getId()));
		modelo.addAttribute("id_emparejado", chat.getEmparejado().getId());
		modelo.addAttribute("id_chat", chat.getId());
		return "listarchat.html :: #chat";
	}

}
