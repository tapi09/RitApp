
package com.RitApp.web.repositorios;

import com.RitApp.web.entidades.Postulante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulanteRepositorio extends JpaRepository<Postulante, String> {

}
