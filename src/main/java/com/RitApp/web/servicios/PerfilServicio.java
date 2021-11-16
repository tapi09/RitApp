package com.RitApp.web.servicios;

import com.RitApp.web.entidades.Perfil;
import com.RitApp.web.repositorios.PerfilRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilServicio {

    @Autowired
    private PerfilRepositorio perfilRepositorio;

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
}
