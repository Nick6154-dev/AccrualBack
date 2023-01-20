package uce.edu.ec.devengamiento.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "universidad")
public class Universidad {
    @Id
    @Column(name = "id_universidad", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_universidad")
    private String nombreUniversidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUniversidad() {
        return nombreUniversidad;
    }

    public void setNombreUniversidad(String nombreUniversidad) {
        this.nombreUniversidad = nombreUniversidad;
    }

}