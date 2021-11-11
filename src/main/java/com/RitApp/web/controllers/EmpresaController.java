package com.RitApp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    /// Registrar Empresa
    @GetMapping("/registrar")
    public ModelAndView FormularioCrear(ModelMap model, @ModelAttribute("msj_error") String msj_error,
            @ModelAttribute("email") String email, @ModelAttribute("contrasea_1") String contrasea_1,
            @ModelAttribute("contrasea_2") String contrasea_2, @ModelAttribute("nombre") String nombre,
            @ModelAttribute("actividad") String actividad, @ModelAttribute("sitioWeb") String sitioWeb,
            @ModelAttribute("beneficios") String beneficios, @ModelAttribute("pais") String pais,
            @ModelAttribute("sobreNosotros") String sobreNosotros) {

        model.put("error", msj_error);
        model.put("email", email);
        model.put("contrasea_1", contrasea_1);
        model.put("contrasea_2", contrasea_2);
        model.put("nombre", nombre);
        model.put("actividad", actividad);
        model.put("sitioWeb", sitioWeb);
        model.put("beneficios", beneficios);
        model.put("pais", pais);
        model.put("sobreNosotros", sobreNosotros);

        return new ModelAndView("Registros/Registrar_Empresa.html", model); // Devuelve al usuario el HTML + El
        // modelo(variables dinamicas)
    }

    @PostMapping("/registrar")
    public RedirectView crear(RedirectAttributes attributes, @RequestParam String email,
            @RequestParam String contrasea_1, @RequestParam String contrasea_2, @RequestParam String nombre,
            @RequestParam String actividad, @RequestParam String sitioWeb, @RequestParam String beneficios,
            @RequestParam String pais, @RequestParam String sobreNosotros) throws Exception {

        try {

            throw new Exception("Error en Registrar Empresa");
//			System.out.println(email);
//			System.out.println(contrasea_1);
//			System.out.println(contrasea_2);
//			System.out.println(nombre);
//			System.out.println(actividad);
//			System.out.println(sitioWeb);
//			System.out.println(beneficios);
//			System.out.println(pais);
//			System.out.println(sobreNosotros);
            // return new RedirectView("/");

        } catch (Exception ex) {
            attributes.addFlashAttribute("email", email);
            attributes.addFlashAttribute("contrasea_1", contrasea_1);
            attributes.addFlashAttribute("contrasea_2", contrasea_2);
            attributes.addFlashAttribute("nombre", nombre);
            attributes.addFlashAttribute("actividad", actividad);
            attributes.addFlashAttribute("sitioWeb", sitioWeb);
            attributes.addFlashAttribute("beneficios", beneficios);
            attributes.addFlashAttribute("pais", pais);
            attributes.addFlashAttribute("sobreNosotros", sobreNosotros);
            attributes.addFlashAttribute("msj_error", ex.getMessage());
            System.err.println(ex.getMessage());

            return new RedirectView("/empresa/registrar");
        }

    }


}
