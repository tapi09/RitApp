
package com.RitApp.web.repositorios;

import com.RitApp.web.entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepositorio extends JpaRepository<Perfil, String> {
    
}
