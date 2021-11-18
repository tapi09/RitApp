
package com.RitApp.web.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RitApp.web.entidades.Emparejado;
import com.RitApp.web.entidades.Empresa;
import com.RitApp.web.entidades.Postulante;

@Repository
public interface EmparejadoRepositorio extends JpaRepository<Emparejado, String> {

	 	List<Emparejado> findByEmpresa(Empresa empresa);
	 	List<Emparejado> findByPostulante(Postulante postulante);	
}