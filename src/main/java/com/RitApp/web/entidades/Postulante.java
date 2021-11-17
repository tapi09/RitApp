package com.RitApp.web.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Postulante extends Usuario {

    private String dni;
    private String nombre;
    private String apellido;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private int edad;
    private Integer telefono;
    private String genero;
    private String direccion;
    private String pais;
    @OneToOne
    private Perfil perfil;
    @OneToMany
    private List<Empresa> likeDeEmpresas;
    @OneToMany
    private List <Trabajo> likeatrabajos;
    @ElementCollection
    private List <String> listaMatch;
    
}
