package com.RitApp.web.entidades;

import java.awt.Image;
import java.io.File;
import java.util.Date;

import javax.persistence.Entity;
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
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class Usuario extends Registro {
<<<<<<< Updated upstream
<<<<<<< Updated upstream

=======
    
>>>>>>> Stashed changes
=======
    
>>>>>>> Stashed changes
    private String dni;
    private String nombre;
    private String apellido;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private int edad;
    private Integer telefono;

<<<<<<< Updated upstream
    private Image foto;
=======
    private Foto foto;
>>>>>>> Stashed changes
    private File cv;
    private String genero;
    private String direccion;
    private String pais;
    @OneToOne
    private Perfil perfil;

    
}
