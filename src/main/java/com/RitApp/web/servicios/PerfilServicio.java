package com.RitApp.web.servicios;

import com.RitApp.web.entidades.Perfil;
import com.RitApp.web.repositorios.PerfilRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilServicio {

    @Autowired
    private PerfilRepositorio perfilRepositorio;
    
    @Transactional
    public Perfil crearPerfil(String lenguaje, String seniority, String idioma, String estudios, String algoSobreMi) throws Exception {
        try {
            Perfil perfil = new Perfil();
            perfil.setLenguaje(lenguaje);
            perfil.setIdioma(idioma);
            perfil.setSeniority(seniority);
            perfil.setEstudios(estudios);
            perfil.setAlgoSobreMi(algoSobreMi);
            perfilRepositorio.save(perfil);

            return perfil;
        } catch (Exception e) {
            throw new Exception("Error al crear Perfil");
        }

    }
    
    @Transactional
    public Perfil crearPerfilVacio() throws Exception {
        try {
            Perfil perfil = new Perfil();
            
            perfilRepositorio.save(perfil);

            return perfil;
        } catch (Exception e) {
            throw new Exception("Error al crear Perfil");
        }

    }
    
    @Transactional(readOnly = true)
    public Perfil buscarPerfilxId(String id) throws Exception {
        try {
            Perfil perfil = perfilRepositorio.getById(id);
            
            return perfil;
        } catch (Exception e) {
            throw new Exception("Error al crear Perfil");
        }

    }
    
    @Transactional
    public Perfil modificarPerfil(String id, String lenguaje, String seniority, String idioma, String estudios, String algoSobreMi) throws Exception {
        try {
            Perfil perfil = perfilRepositorio.getById(id);
            perfil.setLenguaje(lenguaje);
            perfil.setIdioma(idioma);
            perfil.setSeniority(seniority);
            perfil.setEstudios(estudios);
            perfil.setAlgoSobreMi(algoSobreMi);
            perfilRepositorio.saveAndFlush(perfil);

            return perfil;
        } catch (Exception e) {
            throw new Exception("Error al modificar Perfil");
        }

    }
}
