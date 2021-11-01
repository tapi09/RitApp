
package com.RitApp.web.servicios;

import com.RitApp.web.repositorios.LenguajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LenguajeServicio {
    
    @Autowired
    private LenguajeRepositorio lenguajeRepositorio;
}
