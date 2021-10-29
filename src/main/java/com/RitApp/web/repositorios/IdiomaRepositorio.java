
package com.RitApp.web.repositorios;

import com.RitApp.web.entidades.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepositorio extends JpaRepository<Idioma, Integer> {
    
}
