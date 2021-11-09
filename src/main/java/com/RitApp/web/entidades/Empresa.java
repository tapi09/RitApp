
package com.RitApp.web.entidades;

import javax.persistence.Entity;

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
public class Empresa extends Usuario {
    private String nombre;
    private String actividad;
    private String sitioWeb;
    private String beneficios;
    private String sobreNosotros;
    private String pais;
}
