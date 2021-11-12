
package com.RitApp.web.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	private String lenguaje;
	private String tipo;
	private String tiempo;
	/*
	 * @ManyToOne private Empresa empresa;
	 */
	/*
	 * @OneToMany private List<Postulante> listaPostulantes;
	 * 
	 * @OneToMany private List<Postulante> listaMatch;
	 */

}
