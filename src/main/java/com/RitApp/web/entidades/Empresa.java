
package com.RitApp.web.entidades;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
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
    @OneToOne
    private Foto foto;
}
