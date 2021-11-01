
package com.RitApp.web.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Empresa {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String email;
    private String contraseña;
    private String nombre;
    private String actividad;
    private String sitioWeb;
    private String beneficios;
    private String sobreNosotros;
    private String pais;
}
