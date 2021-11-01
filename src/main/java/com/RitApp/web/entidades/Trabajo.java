
package com.RitApp.web.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    @OneToMany
    private List<Usuario> listaPostulantes;
    @OneToMany
    private List<Usuario> listaMatch;
    @ManyToOne
    private Empresa empresa;
}
