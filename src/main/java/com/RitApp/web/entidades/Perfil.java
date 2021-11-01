
package com.RitApp.web.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Perfil {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String gitHubLink;
    private String experienciaLaboral;
    private List<Idioma> idioma;
    private String disponibilidadHoraria;
    private String formacionAcademica;
    private List<Lenguaje> lenguajes;
    private String seniority;
    private String estado;
    private String trabajoActual;
    private int fase;

    
    
    
}
