package com.RitApp.web.controllers;

import com.RitApp.web.entidades.Empresa;
import com.RitApp.web.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RitApp.web.servicios.EmpresaServicio;
import com.RitApp.web.servicios.UsuarioServicio;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    UsuarioServicio usuarioServicio;
    @Autowired
    EmpresaServicio empresaServicio;

    @GetMapping("/registrar")
    public String crear() {

        return "registro_empresa";
    }

    @PostMapping("/registrar")
    public String crear(@RequestParam String nombre, @RequestParam String actividad, @RequestParam String email, @RequestParam String password, @RequestParam String password1) throws Exception {
        empresaServicio.crearEmpresa(email, password, password1, nombre, actividad);
        return "redirect:/";
    }

    @GetMapping("/verPerfil")
    public String verPerfil() {
        return "perfilEmpresa.html";
    }

    @GetMapping("/modificarEmpresa")
    public ModelAndView modificarDatosEmpresa(Authentication usuario) throws Exception {
        try {
            ModelAndView mav = new ModelAndView("/perfilEmpresa");
            Usuario user = usuarioServicio.buscaruserxmail(usuario.getName());
            Empresa empresa = empresaServicio.buscarxid(user.getId());
            mav.addObject("empresa", empresa);
            return mav;
        } catch (Exception e) {
            throw new Exception("Error en Empresa Controlador - modificar Empresa");
        }

    }

    @PostMapping("/modificandoEmpresa")
    public RedirectView modificandoEmpresa(Authentication usuario, @RequestParam String email, @RequestParam String nombre, @RequestParam String actividad, @RequestParam String sitioWeb, @RequestParam String beneficios, @RequestParam String sobreNosotros, @RequestParam String pais, MultipartFile logo) throws Exception {
        try {
            Empresa empresa = empresaServicio.buscarxmail(usuario.getName());
            empresaServicio.modificarEmpresa(empresa.getId(), email, nombre, actividad, sitioWeb, beneficios, sobreNosotros, pais, logo);
            return new RedirectView("/pagina_inicio");
        } catch (Exception e) {
            throw new Exception("Error en EmpresaController - modificandoEmpresa");
        }

    }

}
