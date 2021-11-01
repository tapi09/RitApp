
package com.RitApp.web.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Usuario {
    @Id
    private String dni;
    private String email;
    private String contraseña;
    private String nombre;
    private String apellido;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private int edad;
    private Integer telefono;
//    private String foto;
//    private String cv;
    private String genero;
    private String direccion;
    private String pais;
    @OneToOne
    private Perfil perfil;
}
