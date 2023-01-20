package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "FACULTAD")
public class Facultad {
    @Id
    @Column(name = "ID_FACULTAD", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE_FACULTAD", length = 256)
    private String nombreFacultad;

    @Column(name = "ID_UNIVERSIDAD", nullable = false)
    private Long idUniversidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    public Long getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Long idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

}