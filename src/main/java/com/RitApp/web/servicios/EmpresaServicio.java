
package com.RitApp.web.servicios;

import com.RitApp.web.repositorios.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServicio {
    
    @Autowired
    private EmpresaRepositorio empresaRepositorio;
    
}
