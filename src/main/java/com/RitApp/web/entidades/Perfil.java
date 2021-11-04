
package com.RitApp.web.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Perfil {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String gitHubLink;
    private String experienciaLaboral;
    @OneToMany
    private String idiomas;
    private String disponibilidadHoraria;
    private String formacionAcademica;
    @OneToMany
    private String lenguajes;
    private String seniority;
    private String estado;
    private String trabajoActual;
    private int fase;

    
    
    
}
