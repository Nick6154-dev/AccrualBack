package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "UNIVERSIDAD")
public class Universidad {
    @Id
    @Column(name = "ID_UNIVERSIDAD", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE_UNIVERSIDAD", length = 256)
    private String nombreUniversidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUniversidad() {
        return nombreUniversidad;
    }

    public void setNombreUniversidad(String nombreUniversidad) {
        this.nombreUniversidad = nombreUniversidad;
    }

}