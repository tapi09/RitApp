package com.RitApp.web.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.RitApp.web.entidades.Empresa;
import com.RitApp.web.entidades.Perfil;
import com.RitApp.web.entidades.Postulante;
import com.RitApp.web.entidades.Trabajo;
import com.RitApp.web.entidades.Usuario;
import com.RitApp.web.enums.Rol;
import com.RitApp.web.repositorios.PostulanteRepositorio;
import com.RitApp.web.repositorios.UsuarioRepositorio;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Emiliano
 *
 */
/**
 * @author Emiliano
 *
 */
@Service
public class PostulanteServicio {

    @Autowired
    private PostulanteRepositorio postulanteRepositorio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private PerfilServicio perfilServicio;

    @Transactional
    public void crearPostulante(String nombre, String apellido, String email, String contraseña, String contraseña1,
            Integer telefono) throws Exception {
        validar(nombre, apellido, email, contraseña, contraseña1, telefono);
        Postulante postulante;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        postulante = new Postulante();
        postulante.setEmail(email);
        postulante.setNombre(nombre);
        postulante.setNombre_usuario(nombre);
        postulante.setApellido(apellido);
        postulante.setTelefono(telefono);
        postulante.setClave(encoder.encode(contraseña));
        postulante.setRol(Rol.POSTULANTE);
        Perfil perfil = perfilServicio.crearPerfilVacio();
        postulante.setPerfil(perfil);

        postulanteRepositorio.save(postulante);
    }

    @Transactional
    public void modificar(String id, String nombre, String apellido, String email, Integer telefono, Date fechaNacimiento, Integer edad, String dni, String genero, String pais, String direccion, String lenguaje, String seniority, String idioma, String estudios, String algoSobreMi, MultipartFile foto) throws Exception {
        try{
        System.out.println("Entro a modificar postulante");
        Postulante postulante = postulanteRepositorio.getById(id);

        postulante.setNombre(nombre);
        postulante.setApellido(apellido);
        postulante.setEmail(email);
        postulante.setTelefono(telefono);
        postulante.setFechaNacimiento(fechaNacimiento);
        postulante.setEdad(edad);
        postulante.setDni(dni);
        postulante.setGenero(genero);
        postulante.setDireccion(direccion);
        postulante.setPais(pais);
        
        Perfil perfil = new Perfil();
                
        if(postulante.getPerfil() != null) {
            perfil = perfilServicio.modificarPerfil(postulante.getPerfil().getId() ,lenguaje, seniority, idioma, estudios, algoSobreMi);
            postulante.setPerfil(perfil);
        } else {
            perfil = perfilServicio.crearPerfil(lenguaje, seniority, idioma, estudios, algoSobreMi);
            postulante.setPerfil(perfil);
        }
        
        if (foto != null) {
                postulante.setFoto(foto.getBytes());
            }
        
        postulanteRepositorio.saveAndFlush(postulante);
        } catch  (Exception e) {
            throw new Exception("Error al modificar Postulante");
        }
    }
    
    @Transactional
    public void eliminar(String id) throws Exception {
        Postulante postulante = buscarXId(id);
        postulanteRepositorio.delete(postulante);

    }

    public void validar(String nombre, String apellido, String email, String contraseña, String contraseña1,
            Integer telefono) throws Exception {

        if (email.isEmpty() || email == null) {
            throw new Exception("el email no pude ser nulo");
        }
        if (contraseña.isEmpty() || contraseña == null) {
            throw new Exception("la contraseña no pude ser nula");

        }
        if (!contraseña.equals(contraseña1)) {
            throw new Exception("las contraseñas deben coincidir");

        }
        if (nombre.isEmpty() || nombre == null) {
            throw new Exception("el nombre no pude ser nulo");
        }
        if (apellido.isEmpty() || apellido == null) {
            throw new Exception("el apellido no pude ser nulo");

        }
        if (telefono == null) {
            throw new Exception("el telefono no pude ser nulo");

        }
    }

    public Postulante buscarXId(String id) throws Exception {
        Optional<Postulante> respuesta = postulanteRepositorio.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new Exception("no se encuentra ningun Postulante con el id");
        }
    }

    public List<Postulante> listar() {
        return postulanteRepositorio.findAll();
    }

    public Postulante buscaxmail(String email) {
        Usuario usuario = new Usuario();
        usuario = usuarioServicio.buscaruserxmail(email);
        return postulanteRepositorio.getById(usuario.getId());
    }
}
