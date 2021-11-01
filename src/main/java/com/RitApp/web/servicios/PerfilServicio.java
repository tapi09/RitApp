
package com.RitApp.web.servicios;

import com.RitApp.web.repositorios.PerfilRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilServicio {
    
    @Autowired
    private PerfilRepositorio perfilRepositorio;
}
