package com.RitApp.web.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RitApp.web.entidades.Chat;
import com.RitApp.web.entidades.Emparejado;
import com.RitApp.web.repositorios.ChatRepositorio;
import com.RitApp.web.repositorios.EmparejadoRepositorio;

@Service
public class ChatServicio {
	@Autowired
	ChatRepositorio chatRepositorio;
	@Autowired
	EmparejadoRepositorio emparejadoRepositorio;

	public void guardar_chat(Chat chat) {
		chatRepositorio.save(chat);
	}
	public Chat buscarchatxEmparejado(String id_emparejado) {
		Emparejado emparejado = emparejadoRepositorio.buscarPorid(id_emparejado);
		List<Chat> listchat = chatRepositorio.findByEmparejado(emparejado);
		Chat chat = new Chat();
		for (Chat chat1 : listchat) {
			chat = chat1;
		}
		return chat;		
	}
	public Chat buscarchatxid(String id) {
		return chatRepositorio.buscarPorid(id);
	}
	public List<String> mostrar_mensajes(Chat chat) {
		if (chat.getMensajes().isEmpty() || chat.getMensajes() == null) {
			List<String> lista_mensajeStrings = new ArrayList<String>();
			lista_mensajeStrings.add("Tienes un nuevo match");
			lista_mensajeStrings.add("Puesto: " + chat.getEmparejado().getNombre_puesto());
			chat.setMensajes(lista_mensajeStrings);
			guardar_chat(chat);
			return lista_mensajeStrings;
		} else {
			return chat.getMensajes();
		}
	}

	public void guardar_mensaje(String usuario, String mensaje) {		
		List<String> lista_mensajeStrings = new ArrayList<String>();
	}

	public String generar_Mensaje(String usuario, String mensaje) {
		return "Mensaje de " + usuario + "/n" + mensaje;
	}

}
