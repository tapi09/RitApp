package com.RitApp.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RitApp.web.entidades.Postulante;
import com.RitApp.web.entidades.Trabajo;
import com.RitApp.web.entidades.Usuario;
import com.RitApp.web.servicios.PerfilServicio;
import com.RitApp.web.servicios.PostulanteServicio;
import com.RitApp.web.servicios.TrabajoServicio;
import com.RitApp.web.servicios.UsuarioServicio;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/postulante")
public class PostulanteController {

	@Autowired
	PostulanteServicio service;
	@Autowired
	TrabajoServicio trabajoServicio;
        @Autowired
        UsuarioServicio usuarioServicio;
        @Autowired
        PerfilServicio perfilServicio;
	
	@GetMapping("/listar")

	public String listar(Model model) throws Exception {
		try {

			model.addAttribute("usuarios", service.listar());
		} catch (Exception e) {
			System.err.println("error " + e.getMessage());
			model.addAttribute("error ", e.getMessage());
			return "error.html";

		}
		return "listarUsuarios.html";
	}

	@PostMapping("/generico")
	public String generico() {

		return "/login";
	}

	@PostMapping("/crear")
	public String crear(Model modelo, @RequestParam String nombre, @RequestParam String apellido,
			@RequestParam String email, @RequestParam String contraseña, @RequestParam String contraseña1,
			@RequestParam Integer telefono) throws Exception {
		try {
			service.crearPostulante(nombre, apellido, email, contraseña, contraseña1, telefono);

		} catch (Exception e) {
			System.err.println("error " + e.getMessage());
			modelo.addAttribute("error ", e.getMessage());
			return "error.html";
		}
		return "index.html";
	}

	

	@GetMapping("/eliminar")
	public String eliminar(@RequestParam String id) throws Exception {
		try {
			if (id != null) {
				service.eliminar(id);
			} else {
				throw new Exception("id null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/usuario/listar";

	}

	@GetMapping("/darlike")
	public void darlike() throws Exception {
		System.out.print("entre");

	}
        @GetMapping("/verPerfil")
        public String verPerfil() throws Exception {
            return "perfilPostulante.html";
        }
        
        @GetMapping("/modificarPerfil")
    public ModelAndView modificarDatosPostulante(Authentication usuario) throws Exception {
        try {
            ModelAndView mav = new ModelAndView("/perfilPostulante");
            Usuario user = usuarioServicio.buscaruserxmail(usuario.getName());
            System.out.println(usuario.getName());
            Postulante postulante = service.buscarXId(user.getId());
            
            mav.addObject("postulante", postulante);
           
            return mav;
        } catch (Exception e) {
            throw new Exception("Error en Postulante Controlador - modificar postulante");
        }

    }
    
    @PostMapping("/modificandoPerfil")
    public RedirectView modificandoPostulantePerfil(Authentication usuario, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam Integer telefono, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento, @RequestParam Integer edad, @RequestParam String dni, @RequestParam String genero, @RequestParam String pais, @RequestParam String direccion, @RequestParam String lenguaje, @RequestParam String seniority, @RequestParam String idioma, @RequestParam String estudios, @RequestParam String algoSobreMi, MultipartFile foto) throws Exception {
        try {
            System.out.println(nombre + apellido + email + dni + telefono + idioma + algoSobreMi + lenguaje + estudios + seniority + fechaNacimiento + edad + direccion + pais + genero);
            Postulante postu = service.buscaxmail(usuario.getName());
            service.modificar(postu.getId(), nombre, apellido, email, telefono, fechaNacimiento, edad, dni, genero, pais, direccion, lenguaje, seniority, idioma, estudios, algoSobreMi, foto);
            return new RedirectView("/pagina_inicio");
        } catch (Exception e) {
            throw new Exception("Error en PostulanteController - modificandoPostulante");
        }

    }
}
