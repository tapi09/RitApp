
package com.RitApp.web.servicios;

import com.RitApp.web.repositorios.TrabajoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajoServicio {
    
    @Autowired
    private TrabajoRepositorio trabajoRepositorio;
}
