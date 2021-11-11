package com.RitApp.web.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RitApp.web.repositorios.TrabajoRepositorio;

@Service
public class TrabajoServicio {
    @Autowired
    private TrabajoRepositorio trabajoRepositorio;


}