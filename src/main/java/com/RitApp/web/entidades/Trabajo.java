
package com.RitApp.web.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Trabajo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String puesto;
    private String tipo;
    private int tiempo;
    private double sueldo;
    private String lugar;
    @OneToOne
    private Perfil perfilABuscar;
    private List<Usuario> listaPostulantes;
    private List<Usuario> listaMatch;
    @ManyToOne
    private Empresa empresa;
}
