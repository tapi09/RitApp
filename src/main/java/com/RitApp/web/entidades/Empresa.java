
package com.RitApp.web.entidades;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class Empresa extends Registro {
    
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    private String nombre;
    private String actividad;
    private String sitioWeb;
    private String beneficios;
    private String sobreNosotros;
    private String pais;
    private Foto foto;
}
