

package com.RitApp.web.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RitApp.web.entidades.Empresa;
import com.RitApp.web.entidades.Usuario;
import com.RitApp.web.enums.Rol;
import com.RitApp.web.repositorios.EmpresaRepositorio;
import com.RitApp.web.repositorios.UsuarioRepositorio;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmpresaServicio {
    
    @Autowired
    private EmpresaRepositorio empresaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @Transactional
    public void crearEmpresa(String email, String contraseña1, String contraseña2, String nombre, String actividad) throws Exception {
        try {
            validarContraseña(contraseña1, contraseña2);
            BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
            Empresa empresa = new Empresa();
            empresa.setEmail(email);
            empresa.setClave(encoder.encode(contraseña1));
            empresa.setNombre(nombre);
            empresa.setNombre_usuario(nombre);
            empresa.setActividad(actividad);
            empresa.setRol(Rol.EMPRESA);

            
            empresaRepositorio.save(empresa);
            
        } catch (Exception e) {
            throw new Exception("Error al Crear Empresa en EmpresaServicio");
        }
    }
    
    @Transactional
    public void modificarEmpresa(String id, String email, String nombre, String actividad, String sitioWeb, String beneficios, String sobreNostros, String pais, MultipartFile logo) throws Exception {
        try {
            Empresa empresa = empresaRepositorio.getById(id);
            empresa.setEmail(email);
            empresa.setNombre(nombre);
            empresa.setActividad(actividad);
            empresa.setSitioWeb(sitioWeb);
            empresa.setBeneficios(beneficios);
            empresa.setSobreNosotros(sobreNostros);
            empresa.setPais(pais);
            
            if (logo != null) {
                empresa.setFoto(logo.getBytes());
            }

            empresaRepositorio.saveAndFlush(empresa);

        } catch (Exception e) {
            throw new Exception("Error al Modificar Empresa en EmpresaServicio");
        }
    }
    
    @Transactional
    public void eliminarEmpresa(String id) throws Exception {
        try {
            empresaRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Error al Eliminar Empresa en EmpresaServicio");
        }
    }
    
    @Transactional(readOnly = true)
    public List<Empresa> imprimirEmpresas() throws Exception {
        try {
            List<Empresa> lista = empresaRepositorio.findAll();
            return lista;
        } catch (Exception e) {
            throw new Exception("Error al imprimir todas las empresas");
        }

    }
    
    @Transactional
    public void validarContraseña(String clave1, String clave2) throws Exception {
        try {
            if(clave1.isEmpty()) {
                throw new Exception("La contraseña esta vacia");
            }
            if(clave2.isEmpty()) {
                throw new Exception("La contraseña esta vacia");
            }
            if(clave1.equals(clave2)) {
                
            } else {
                throw new Exception("La contraseñas son diferentes");
            }
        } catch (Exception e) {
            throw new Exception("Error, las dos contraseñas son diferentes o nulas");
        }
        
        
    }
    
    @Transactional(readOnly = true)
    public List<Empresa> buscarEmpresasXNombre(String nombre) throws Exception {
        try {
            List<Empresa> listado = empresaRepositorio.findAll();
            List<Empresa> empresas = new ArrayList<Empresa>();
            for (Empresa e : listado) {
                if (nombre.equals(e.getNombre())) {
                    empresas.add(e);
                }
            }

            if (empresas != null) {
                return empresas;
            } else {
                //Aca se entrega una lista vacia, en caso de que no se encuentre ninguna empresa
                return empresas;
            }

        } catch (Exception e) {
            throw new Exception("Error al buscar empresas por Nombre");
        }

    }
    public Empresa buscarxmail(String email) {
    	Usuario usuario=new Usuario();
    	usuario=usuarioRepositorio.buscarPorEmail(email);
    	return empresaRepositorio.getById(usuario.getId());
    }
    
    public Empresa buscarxid(String id) {
        Empresa empresa = new Empresa();
        empresa = empresaRepositorio.getById(id);
        return empresa;
    }      
    
}
